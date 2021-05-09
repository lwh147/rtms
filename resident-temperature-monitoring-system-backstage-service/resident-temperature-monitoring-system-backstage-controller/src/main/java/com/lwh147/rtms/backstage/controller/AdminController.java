package com.lwh147.rtms.backstage.controller;

import cn.hutool.core.bean.BeanException;
import com.lwh147.rtms.backstage.api.AdminControllerApi;
import com.lwh147.rtms.backstage.common.annotation.Page;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.controller.exception.code.ControllerExceptionCode;
import com.lwh147.rtms.backstage.pojo.dto.AdminDTO;
import com.lwh147.rtms.backstage.pojo.query.AdminQuery;
import com.lwh147.rtms.backstage.pojo.vo.AdminVO;
import com.lwh147.rtms.backstage.pojo.vo.LoginFormVO;
import com.lwh147.rtms.backstage.serve.AdminService;
import com.lwh147.rtms.backstage.util.BeanUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_EMPTY_ERROR);
        }
        AdminDTO adminDTO = new AdminDTO();
        try {
            BeanUtil.copy(adminVO, adminDTO);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
        return adminService.add(adminDTO);
    }

    @Override
    @DeleteMapping("")
    public Boolean delete(@RequestParam("id") Long id) {
        if (id == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTY_ERROR);
        }
        return adminService.delete(id);
    }

    @Override
    @GetMapping("{id}")
    public AdminVO queryById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTY_ERROR);
        }
        AdminDTO adminDTO = adminService.queryById(id);
        if (adminDTO == null) {
            return null;
        }
        try {
            return BeanUtil.copy(adminDTO, AdminVO.class);
        } catch (BeanException e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
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
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_VO_EMPTY_ERROR);
        }
        if (adminVO.getId() == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_ARGUMENT_ID_EMPTY_ERROR);
        }
        AdminDTO adminDTO = new AdminDTO();
        try {
            BeanUtil.copy(adminVO, adminDTO);
        } catch (Exception e) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_BEAN_COPY_ERROR);
        }
        return adminService.update(adminDTO);
    }

    @Override
    @PostMapping("loginFromApp")
    public AdminVO loginFromApp(@RequestBody LoginFormVO loginFormVO) {
        if (loginFormVO.getAccount() != null && loginFormVO.getPassword() != null) {
            AdminDTO adminDTO = adminService.loginFromApp(BeanUtil.copy(loginFormVO, AdminQuery.class));
            if (adminDTO == null) {
                throw new CommonException(ControllerExceptionCode.CONTROLLER_LOGIN_FAIL);
            }
            return BeanUtil.copy(adminDTO, AdminVO.class);
        }
        throw new CommonException(ControllerExceptionCode.CONTROLLER_LOGIN_ARGUMENT_EMPTY_ERROR);
    }

    @Override
    @PostMapping("loginFromBackstage")
    public Map<String, Object> loginFromBackstage(@RequestBody LoginFormVO loginFormVO) {
        if (loginFormVO == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_LOGIN_ARGUMENT_EMPTY_ERROR);
        }
        if (loginFormVO.getAccount() == null || loginFormVO.getPassword() == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_LOGIN_ARGUMENT_LOSE_ERROR);
        }
        AdminDTO adminDTO = adminService.loginFromBackstage(BeanUtil.copy(loginFormVO, AdminQuery.class));
        if (adminDTO == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_LOGIN_FAIL);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", "testToken");
        return resultMap;
    }

    @Override
    @GetMapping("info")
    public Map<String, Object> getAdminInfo(@RequestParam("token") String token) {
        if (token == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_LOGIN_HAS_NO_TOKEN_ERROR);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("name", "admin");
        resultMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return resultMap;
    }

    @Override
    @PostMapping("logout")
    public Boolean logout() {
        return true;
    }
}
