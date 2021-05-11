package com.lwh147.rtms.backstage.controller;

import cn.hutool.core.bean.BeanException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lwh147.rtms.backstage.api.TempInfoControllerApi;
import com.lwh147.rtms.backstage.common.annotation.Page;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.controller.exception.code.ControllerExceptionCode;
import com.lwh147.rtms.backstage.pojo.dto.TempInfoDTO;
import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;
import com.lwh147.rtms.backstage.pojo.vo.TempInfoVO;
import com.lwh147.rtms.backstage.pojo.vo.TempInfoWithFaceVO;
import com.lwh147.rtms.backstage.serve.TempInfoService;
import com.lwh147.rtms.backstage.util.BeanUtil;
import com.lwh147.rtms.backstage.util.DateTimeUtil;
import com.lwh147.rtms.backstage.util.RequestUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @description: 温度信息控制器接口实现
 * @author: lwh
 * @create: 2021/5/3 21:05
 * @version: v1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/tempInfo")
public class TempInfoController implements TempInfoControllerApi {
    public static final String token_api = "https://aip.baidubce.com/oauth/2.0/token";
    public static final String search_api = "https://aip.baidubce.com/rest/2.0/face/v3/search";

    public static final String grant_type = "client_credentials";
    public static final String client_id = "GYmnWyBhREin3H1lUa6vqNo0";
    public static final String client_secret = "bWsTZg7Quc3ZDDUYwsQKdKVIIQKzrOWs";

    public static final String group_id_list_1 = "1_1,1_2,3_1,3_2,5_1,5_2,7_1,7_2,9_1,9_2";
    public static final String group_id_list_2 = "2_1,2_2,4_1,4_2,6_1,6_2,8_1,8_2,10_1,10_2";

    @Resource
    private TempInfoService tempInfoService;

    @Override
    @PostMapping("")
    public Boolean add(@RequestBody TempInfoVO tempInfoVO) {
        if (tempInfoVO == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_EMPTY_ERROR);
        }
        if (tempInfoVO.getTemp() == null || tempInfoVO.getTime() == null || tempInfoVO.getResidentId() == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_PROPERTY_LOSE_ERROR);
        }
        TempInfoDTO tempInfoDTO = new TempInfoDTO();
        try {
            BeanUtil.copy(tempInfoVO, tempInfoDTO);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
        return tempInfoService.add(tempInfoDTO);
    }

    @Override
    @PostMapping("/addWithFace")
    public Boolean add(@RequestBody TempInfoWithFaceVO tempInfoWithFace) {
        if (tempInfoWithFace == null
                || tempInfoWithFace.getTime() == null
                || tempInfoWithFace.getTemp() == null
                || tempInfoWithFace.getPicture() == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_LOSE_ERROR);
        }
        TempInfoDTO tempInfoDTO = new TempInfoDTO();
        tempInfoDTO.setTime(tempInfoWithFace.getTime());
        tempInfoDTO.setTemp(tempInfoWithFace.getTemp());
        Map<String, String> getTokenParams = new HashMap<>();
        getTokenParams.put("grant_type", grant_type);
        getTokenParams.put("client_id", client_id);
        getTokenParams.put("client_secret", client_secret);

        String result = null;
        try {
            result = RequestUtil.Post(token_api, "", getTokenParams);
        } catch (IOException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_REQUEST_FAIL_ERROR);
        }
        JSONObject jsonObject = JSON.parseObject(result);
        Map<String, String> token = new HashMap<>();
        token.put("access_token", jsonObject.getString("access_token"));
        Map<String, String> data = new HashMap<>();
        data.put("image", tempInfoWithFace.getPicture());
        data.put("image_type", "BASE64");
        data.put("group_id_list", group_id_list_1);
        String searchResult = null;
        try {
            searchResult = RequestUtil.Post(search_api, JSON.toJSONString(data), token);
        } catch (IOException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_REQUEST_FAIL_ERROR);
        }
        JSONArray userList = JSON.parseObject(searchResult).getJSONObject("result").getJSONArray("user_list");
        if (userList.size() > 0 && userList.getJSONObject(0).getFloat("score") > 80.0f) {
            tempInfoDTO.setResidentId(userList.getJSONObject(0).getLong("user_id"));
            return tempInfoService.add(tempInfoDTO);
        } else {
            data.put("group_id_list", group_id_list_2);
            try {
                searchResult = RequestUtil.Post(search_api, JSON.toJSONString(data), token);
            } catch (IOException e) {
                throw new CommonException(ControllerExceptionCode.CONTROLLER_REQUEST_FAIL_ERROR);
            }
            userList = JSON.parseObject(searchResult).getJSONObject("result").getJSONArray("user_list");
            if (userList.size() > 0 && userList.getJSONObject(0).getFloat("score") > 80.0f) {
                tempInfoDTO.setResidentId(userList.getJSONObject(0).getLong("user_id"));
                return tempInfoService.add(tempInfoDTO);
            } else {
                throw new CommonException(ControllerExceptionCode.CONTROLLER_NO_SUCH_PEOPLE_ERROR);
            }
        }
    }

    @Override
    @GetMapping("")
    @Page
    public List<TempInfoVO> commonQuery(TempInfoQuery tempInfoQuery) {
        if (tempInfoQuery == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_QUERY_EMPTY_ERROR);
        }
        if (tempInfoQuery.getStartTime() == null) {
            // 使用days参数构造起始和截止时间戳，便于sql比较
            Date startDate = DateTimeUtil.getPlusDayInitial(new Date(), -tempInfoQuery.getDays() + 1);
            tempInfoQuery.setStartTime(startDate);
            tempInfoQuery.setEndTime(null);
        }
        List<TempInfoDTO> tempInfoDTOS = tempInfoService.commonQuery(tempInfoQuery);
        try {
            return BeanUtil.copyList(tempInfoDTOS, TempInfoVO.class);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    @GetMapping("total")
    public Integer total(TempInfoQuery tempInfoQuery) {
        if (tempInfoQuery == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_LOSE_ERROR);
        }
        return tempInfoService.getTotal(tempInfoQuery);
    }

    @Override
    @GetMapping("charData")
    public List<Map<String, Object>> charData(TempInfoQuery tempInfoQuery) {
        if (tempInfoQuery == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_LOSE_ERROR);
        }
        if (tempInfoQuery.getDays() == null || tempInfoQuery.getNormal() == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_LOSE_ERROR);
        }
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(7);
        set.add(15);
        if (!set.contains(tempInfoQuery.getDays())) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_NOT_VALID_ERROR);
        }
        return tempInfoService.getTemp(tempInfoQuery);
    }

    @Override
    @GetMapping("charData/resident")
    public List<Map<String, Object>> getTempByResidentId(TempInfoQuery tempInfoQuery) {
        if (tempInfoQuery == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_QUERY_EMPTY_ERROR);
        }
        if (tempInfoQuery.getStartTime() == null) {
            // 使用days参数构造起始和截止时间戳，便于sql比较
            Date startDate = DateTimeUtil.getPlusDayInitial(new Date(), -tempInfoQuery.getDays() + 1);
            tempInfoQuery.setStartTime(startDate);
            tempInfoQuery.setEndTime(null);
        }
        return tempInfoService.getTempByResidentId(tempInfoQuery);
    }

    @Override
    @GetMapping("charData/all")
    public List<Map<String, Object>> getTempOf15() {
        return tempInfoService.getTempOf15();
    }


}
