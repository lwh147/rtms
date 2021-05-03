package com.lwh147.rtms.backstage.dao.mapper;

import com.lwh147.rtms.backstage.dao.entity.Resident;
import com.lwh147.rtms.backstage.pojo.query.ResidentQuery;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResidentMapper extends Mapper<Resident> {
    /**
     * admin通用查询
     * @param residentQuery 查询条件
     * @return java.util.List<com.lwh147.rtms.backstage.dao.entity.Resident>
     **/
    List<Resident> commonQuery(ResidentQuery residentQuery);
}