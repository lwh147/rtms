package com.lwh147.rtms.backstage.serve.impl;

import cn.hutool.core.bean.BeanException;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lwh147.rtms.backstage.common.context.BaseContextHolder;
import com.lwh147.rtms.backstage.common.exception.CommonException;
import com.lwh147.rtms.backstage.dao.entity.TempInfo;
import com.lwh147.rtms.backstage.dao.mapper.TempInfoMapper;
import com.lwh147.rtms.backstage.pojo.dto.TempInfoDTO;
import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;
import com.lwh147.rtms.backstage.serve.TempInfoService;
import com.lwh147.rtms.backstage.serve.exception.code.BusinessExceptionCode;
import com.lwh147.rtms.backstage.util.BeanUtil;
import com.lwh147.rtms.backstage.util.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 体温信息业务接口实现
 * @author: lwh
 * @create: 2021/5/3 21:17
 * @version: v1.0
 **/
@Service
public class TempInfoServiceImpl implements TempInfoService {
    @Resource
    private TempInfoMapper tempInfoMapper;
    @Resource
    private IdGenerator idGenerator;

    @Override
    public Boolean add(TempInfoDTO tempInfoDTO) {
        TempInfo tempInfo = new TempInfo();
        try {
            BeanUtil.copy(tempInfoDTO, tempInfo);
        } catch (BeanException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
        tempInfo.setId(idGenerator.snowflakeId());
        return tempInfoMapper.selectedInsert(tempInfo) > 0;
    }

    @Override
    public List<TempInfoDTO> commonQuery(TempInfoQuery tempInfoQuery) {
        List<TempInfo> tempInfos = tempInfoMapper.commonQuery(tempInfoQuery);
        if (tempInfos == null) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_QUERY_RESULT_SET_NULL_ERROR);
        }
        // 获取并设置分页信息
        String pageInfo = JSON.toJSONString(new PageInfo<>(tempInfos));
        BaseContextHolder.setPageInfo(pageInfo);
        try {
            return BeanUtil.copyList(tempInfos, TempInfoDTO.class);
        } catch (BeanException e) {
            throw new CommonException(BusinessExceptionCode.BUSINESS_BEAN_COPY_ERROR.getCode(), e.getMessage());
        }
    }
}
