package com.lwh147.rtms.backstage.dao.mapper;

import com.lwh147.rtms.backstage.dao.entity.TempInfo;
import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取总记录数
     *
     * @param tempInfoQuery
     * @return java.lang.Integer
     **/
    Integer getTotal(TempInfoQuery tempInfoQuery);

    /**
     * 根据条件获取图表数据
     *
     * @param tempInfoQuery
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    List<Map<String, Object>> getTemp(TempInfoQuery tempInfoQuery);

    /**
     * 根据条件获取某个人的体温数据
     *
     * @param tempInfoQuery
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    List<Map<String, Object>> getTempByResidentId(TempInfoQuery tempInfoQuery);

    /**
     * 获取15天内所有记录
     *
     * @param
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    List<Map<String, Object>> getTempOf15();
}