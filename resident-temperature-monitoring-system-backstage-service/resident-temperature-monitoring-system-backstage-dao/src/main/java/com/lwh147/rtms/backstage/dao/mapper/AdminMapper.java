package com.lwh147.rtms.backstage.dao.mapper;

import com.lwh147.rtms.backstage.dao.entity.Admin;
import com.lwh147.rtms.backstage.pojo.query.AdminQuery;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdminMapper extends Mapper<Admin> {
    /**
     * admin通用查询
     * @param adminQuery 查询条件
     * @return java.util.List<com.lwh147.rtms.backstage.dao.entity.Admin>
     **/
    List<Admin> commonQuery(AdminQuery adminQuery);
}