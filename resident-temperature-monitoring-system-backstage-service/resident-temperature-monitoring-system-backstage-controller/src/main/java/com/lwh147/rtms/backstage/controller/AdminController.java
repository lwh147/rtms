package com.lwh147.rtms.backstage.controller;

import cn.hutool.core.bean.BeanException;
import com.alibaba.fastjson.JSON;
import com.lwh147.rtms.backstage.api.AdminControllerApi;
import com.lwh147.rtms.backstage.common.annotation.Page;
import com.lwh147.rtms.backstage.common.config.RedisUtils;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.common.exception.code.CommonExceptionCodeImpl;
import com.lwh147.rtms.backstage.controller.exception.code.ControllerExceptionCode;
import com.lwh147.rtms.backstage.pojo.dto.AdminDTO;
import com.lwh147.rtms.backstage.pojo.query.AdminQuery;
import com.lwh147.rtms.backstage.pojo.vo.AdminVO;
import com.lwh147.rtms.backstage.pojo.vo.LoginFormVO;
import com.lwh147.rtms.backstage.serve.AdminService;
import com.lwh147.rtms.backstage.util.BeanUtil;
import com.lwh147.rtms.backstage.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 管理员控制器接口实现
 * @author: lwh
 * @create: 2021/4/30 11:25
 * @version: v1.0
 **/
@RestController
// @CrossOrigin
@RequestMapping("/admin")
@Slf4j
public class AdminController implements AdminControllerApi {
    @Resource
    private AdminService adminService;
    @Resource
    private RedisUtils redisUtils;

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
        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("name", "admin");
        userInfo.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

        String token = JWTUtils.createToken();
        redisUtils.expireSet(token, userInfo, 15 * 60);
        String refreshToken = JWTUtils.createRefreshToken();
        redisUtils.expireSet(refreshToken, "refresh_token", 7 * 24 * 60 * 60);

        resultMap.put("access_token", token);
        resultMap.put("refresh_token", refreshToken);

        return resultMap;
    }

    @GetMapping("loginFromBackstage/refresh")
    public Map<String, Object> refreshToken() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String refreshToken = request.getHeader("X-Token");
        Object obj = redisUtils.get(refreshToken);
        if (Objects.isNull(obj)) {
            throw new CommonException(CommonExceptionCodeImpl.COMMON_NOT_LOGIN_ERROR);
        }
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("name", "admin");
        userInfo.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

        String token = JWTUtils.createToken();
        redisUtils.expireSet(token, userInfo, 15 * 60);

        log.info("设置了新的token到redis中：{}", token);

        resultMap.put("access_token", token);

        return resultMap;
    }

    @Override
    @GetMapping("info")
    public Map<?, ?> getAdminInfo(@RequestParam("token") String token) {
        if (token == null) {
            throw new CommonException(ControllerExceptionCode.CONTROLLER_LOGIN_HAS_NO_TOKEN_ERROR);
        }
        Object object = redisUtils.get(token);
        if (Objects.isNull(object)) {
            throw new CommonException(CommonExceptionCodeImpl.COMMON_NOT_LOGIN_ERROR);
        }
        return JSON.parseObject(JSON.toJSONString(object), Map.class);
    }

    @Override
    @PostMapping("logout")
    public Boolean logout(@RequestBody Map<String, String> map) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        redisUtils.remove(request.getHeader("X-Token"));
        log.info("接收到的refreshToken: {}", map.get("refreshToken"));
        redisUtils.remove(map.get("refreshToken"));
        return true;
    }
}
