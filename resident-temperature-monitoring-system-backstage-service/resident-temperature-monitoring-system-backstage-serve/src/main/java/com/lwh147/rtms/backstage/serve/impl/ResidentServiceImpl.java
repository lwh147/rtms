package com.lwh147.rtms.backstage.serve.impl;

import cn.hutool.core.bean.BeanException;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lwh147.rtms.backstage.common.context.BaseContextHolder;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.dao.entity.Resident;
import com.lwh147.rtms.backstage.dao.mapper.ResidentMapper;
import com.lwh147.rtms.backstage.pojo.dto.ResidentDTO;
import com.lwh147.rtms.backstage.pojo.query.ResidentQuery;
import com.lwh147.rtms.backstage.serve.ResidentService;
import com.lwh147.rtms.backstage.serve.exception.code.BusinessExceptionCode;
import com.lwh147.rtms.backstage.util.BeanUtil;
import com.lwh147.rtms.backstage.util.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 居民业务接口实现
 * @author: lwh
 * @create: 2021/5/3 12:22
 * @version: v1.0
 **/
@Service
public class ResidentServiceImpl implements ResidentService {
    @Resource
    private ResidentMapper residentMapper;
    @Resource
    private IdGenerator idGenerator;

    @Override
    public Boolean add(ResidentDTO residentDTO) {
        Resident resident = new Resident();
        try {
            BeanUtil.copy(residentDTO, resident);
        } catch (BeanException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
        resident.setId(idGenerator.snowflakeId());
        return residentMapper.insert(resident) > 0;
    }

    @Override
    public Boolean delete(Long id) {
        return residentMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public ResidentDTO queryById(Long id) {
        Resident resident = residentMapper.selectByPrimaryKey(id);
        if (resident == null) {
            return null;
        }
        try {
            return BeanUtil.copy(resident, ResidentDTO.class);
        } catch (BeanException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public Boolean update(ResidentDTO residentDTO) {
        Resident resident = new Resident();
        try {
            BeanUtil.copy(residentDTO, resident);
        } catch (BeanException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
        return residentMapper.updateByPrimaryKeySelective(resident) > 0;
    }

    @Override
    public List<ResidentDTO> commonQuery(ResidentQuery residentQuery) {
        List<Resident> residents = residentMapper.commonQuery(residentQuery);
        if (residents == null) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_QUERY_RESULT_SET_NULL_ERROR);
        }
        // 获取并设置分页信息
        String pageInfo = JSON.toJSONString(new PageInfo<>(residents));
        BaseContextHolder.setPageInfo(pageInfo);
        try {
            return BeanUtil.copyList(residents, ResidentDTO.class);
        } catch (BeanException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
    }
}
