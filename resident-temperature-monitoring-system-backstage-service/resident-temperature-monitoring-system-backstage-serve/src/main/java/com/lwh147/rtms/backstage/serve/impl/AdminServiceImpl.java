package com.lwh147.rtms.backstage.serve.impl;

import cn.hutool.core.bean.BeanException;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lwh147.rtms.backstage.common.context.BaseContextHolder;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.dao.entity.Admin;
import com.lwh147.rtms.backstage.dao.mapper.AdminMapper;
import com.lwh147.rtms.backstage.pojo.dto.AdminDTO;
import com.lwh147.rtms.backstage.pojo.query.AdminQuery;
import com.lwh147.rtms.backstage.serve.AdminService;
import com.lwh147.rtms.backstage.serve.exception.code.BusinessExceptionCode;
import com.lwh147.rtms.backstage.util.BeanUtil;
import com.lwh147.rtms.backstage.util.IdGenerator;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @description: 管理员服务接口实现
 * @author: lwh
 * @create: 2021/4/30 17:09
 * @version: v1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private IdGenerator idGenerator;

    @Override
    public Boolean add(AdminDTO adminDTO) {
        Admin admin = new Admin();
        try {
            BeanUtils.copyProperties(admin, adminDTO);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR);
        }
        admin.setId(idGenerator.snowflakeId());
        return adminMapper.insert(admin) > 0;
    }

    @Override
    public Boolean delete(Long id) {
        return adminMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public AdminDTO queryById(Long id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if (admin == null) {
            return null;
        }
        AdminDTO adminDTO = new AdminDTO();
        try {
            BeanUtils.copyProperties(adminDTO, admin);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR);
        }
        return adminDTO;
    }

    @Override
    public Boolean update(AdminDTO adminDTO) {
        Admin admin = new Admin();
        try {
            BeanUtils.copyProperties(admin, adminDTO);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR);
        }
        return adminMapper.updateByPrimaryKeySelective(admin) > 0;
    }

    @Override
    public List<AdminDTO> commonQuery(AdminQuery adminQuery) {
        List<Admin> admins = adminMapper.commonQuery(adminQuery);
        if (admins == null) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_QUERY_RESULT_SET_NULL_ERROR);
        }
        // 获取并设置分页信息
        String pageInfo = JSON.toJSONString(new PageInfo<>(admins));
        BaseContextHolder.setPageInfo(pageInfo);
        try {
            return BeanUtil.copyList(admins, AdminDTO.class);
        } catch (BeanException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
    }
}
