package com.lwh147.rtms.backstage.controller;

import cn.hutool.core.bean.BeanException;
import com.lwh147.rtms.backstage.api.TempInfoControllerApi;
import com.lwh147.rtms.backstage.common.annotation.Page;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.controller.exception.code.ControllerExceptionCode;
import com.lwh147.rtms.backstage.pojo.dto.TempInfoDTO;
import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;
import com.lwh147.rtms.backstage.pojo.vo.TempInfoVO;
import com.lwh147.rtms.backstage.serve.TempInfoService;
import com.lwh147.rtms.backstage.util.BeanUtil;
import com.lwh147.rtms.backstage.util.DateTimeUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @GetMapping("/total")
    public Integer total(TempInfoQuery tempInfoQuery) {
        if (tempInfoQuery == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_LOSE_ERROR);
        }
        return tempInfoService.getTotal(tempInfoQuery);
    }

    @Override
    @GetMapping("/charData")
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
    @GetMapping("/charData/resident")
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
    @GetMapping("/charData/all")
    public List<Map<String, Object>> getTempOf15() {
        return tempInfoService.getTempOf15();
    }
}
