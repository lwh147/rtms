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
import java.util.Date;
import java.util.List;

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
    public Boolean add(TempInfoVO tempInfoVO) {
        if (tempInfoVO == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_EMPTUY_ERROR);
        }
        TempInfoDTO tempInfoDTO = new TempInfoDTO();
        try {
            BeanUtil.copy(tempInfoVO, tempInfoDTO);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
        return null;
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
}
