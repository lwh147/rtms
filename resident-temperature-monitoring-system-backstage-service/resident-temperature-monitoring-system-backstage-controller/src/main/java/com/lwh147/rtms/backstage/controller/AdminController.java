package com.lwh147.rtms.backstage.controller;

import cn.hutool.core.bean.BeanException;
import com.lwh147.rtms.backstage.api.AdminControllerApi;
import com.lwh147.rtms.backstage.common.annotation.Page;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.controller.exception.code.ControllerExceptionCode;
import com.lwh147.rtms.backstage.pojo.dto.AdminDTO;
import com.lwh147.rtms.backstage.pojo.query.AdminQuery;
import com.lwh147.rtms.backstage.pojo.vo.AdminVO;
import com.lwh147.rtms.backstage.serve.AdminService;
import com.lwh147.rtms.backstage.util.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @description: 管理员控制器接口实现
 * @author: lwh
 * @create: 2021/4/30 11:25
 * @version: v1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController implements AdminControllerApi {
    @Resource
    private AdminService adminService;

    @Override
    @PostMapping("")
    public Boolean add(@RequestBody AdminVO adminVO) {
        if (adminVO == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_EMPTUY_ERROR);
        }
        AdminDTO adminDTO = new AdminDTO();
        try {
            BeanUtils.copyProperties(adminDTO, adminVO);
        } catch (Exception e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR);
        }
        return adminService.add(adminDTO);
    }

    @Override
    @DeleteMapping("")
    public Boolean delete(@RequestParam("id") Long id) {
        if (id == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTUY_ERROR);
        }
        return adminService.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public AdminVO queryById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTUY_ERROR);
        }
        AdminVO adminVO = new AdminVO();
        AdminDTO adminDTO = adminService.queryById(id);
        if (adminDTO != null) {
            try {
                BeanUtils.copyProperties(adminVO, adminDTO);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR);
            }
            return adminVO;
        }
        return null;
    }

    @Override
    @Page
    @GetMapping("")
    public List<AdminVO> commonQuery(AdminQuery adminQuery) {
        if (adminQuery == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_QUERY_EMPTY_ERROR);
        }
        List<AdminDTO> adminDTOS = adminService.commonQuery(adminQuery);
        try {
            return BeanUtil.copyList(adminDTOS, AdminVO.class);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    @PutMapping("")
    public Boolean update(@RequestBody AdminVO adminVO) {
        if (adminVO == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_EMPTUY_ERROR);
        }
        if (adminVO.getId() == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTUY_ERROR);
        }
        AdminDTO adminDTO = new AdminDTO();
        try {
            BeanUtils.copyProperties(adminDTO, adminVO);
        } catch (Exception e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR);
        }
        return adminService.update(adminDTO);
    }
}
