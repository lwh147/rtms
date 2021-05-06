package com.lwh147.rtms.backstage.dao.mapper;

import com.lwh147.rtms.backstage.dao.entity.TempInfo;
import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TempInfoMapper extends Mapper<TempInfo> {
    /**
     * tempInfo通用查询
     *
     * @param tempInfoQuery 查询条件
     * @return java.util.List<com.lwh147.rtms.backstage.dao.entity.TempInfo>
     **/
    List<TempInfo> commonQuery(TempInfoQuery tempInfoQuery);

    /**
     * tempInfo插入
     *
     * @param tempInfo 要插入的实体
     * @return java.util.List<com.lwh147.rtms.backstage.dao.entity.TempInfo>
     **/
    int selectedInsert(TempInfo tempInfo);
}