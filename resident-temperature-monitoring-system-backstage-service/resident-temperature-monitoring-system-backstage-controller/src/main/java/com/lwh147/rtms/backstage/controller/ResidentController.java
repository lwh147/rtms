package com.lwh147.rtms.backstage.controller;

import cn.hutool.core.bean.BeanException;
import com.lwh147.rtms.backstage.api.ResidentControllerApi;
import com.lwh147.rtms.backstage.common.annotation.Page;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.controller.exception.code.ControllerExceptionCode;
import com.lwh147.rtms.backstage.pojo.dto.ResidentDTO;
import com.lwh147.rtms.backstage.pojo.query.ResidentQuery;
import com.lwh147.rtms.backstage.pojo.vo.ResidentVO;
import com.lwh147.rtms.backstage.serve.ResidentService;
import com.lwh147.rtms.backstage.util.BeanUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 居民控制器接口实现
 * @author: lwh
 * @create: 2021/5/3 12:16
 * @version: v1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/resident")
public class ResidentController implements ResidentControllerApi {
    @Resource
    private ResidentService residentService;

    @Override
    @PostMapping("")
    public Boolean add(@RequestBody ResidentVO residentVO) {
        if (residentVO == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_EMPTUY_ERROR);
        }
        ResidentDTO residentDTO = new ResidentDTO();
        try {
            BeanUtil.copy(residentVO, residentDTO);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
        return residentService.add(residentDTO);
    }

    @Override
    @DeleteMapping("")
    public Boolean delete(@RequestParam("id") Long id) {
        if (id == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTUY_ERROR);
        }
        return residentService.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResidentVO queryById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTUY_ERROR);
        }
        ResidentDTO residentDTO = residentService.queryById(id);
        if (residentDTO == null) {
            return null;
        }
        try {
            return BeanUtil.copy(residentDTO, ResidentVO.class);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    @Page
    @GetMapping("")
    public List<ResidentVO> commonQuery(ResidentQuery residentQuery) {
        if (residentQuery == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_QUERY_EMPTY_ERROR);
        }
        List<ResidentDTO> residentDTOS = residentService.commonQuery(residentQuery);
        try {
            return BeanUtil.copyList(residentDTOS, ResidentVO.class);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    @PutMapping("")
    public Boolean update(@RequestBody ResidentVO residentVO) {
        if (residentVO == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_EMPTUY_ERROR);
        }
        if (residentVO.getId() == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTUY_ERROR);
        }
        ResidentDTO residentDTO = new ResidentDTO();
        try {
            BeanUtil.copy(residentVO, residentDTO);
        } catch (Exception e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
        return residentService.update(residentDTO);
    }
}
