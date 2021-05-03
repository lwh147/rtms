package com.lwh147.rtms.backstage.serve;

import com.lwh147.rtms.backstage.pojo.dto.AdminDTO;
import com.lwh147.rtms.backstage.pojo.query.AdminQuery;

import java.util.List;

/**
 * @description: 管理员服务接口
 * @author: lwh
 * @create: 2021/4/30 17:06
 * @version: v1.0
 **/
public interface AdminService {
    /**
     * 添加管理员
     *
     * @param adminDTO
     * @return java.lang.Boolean
     **/
    Boolean add(AdminDTO adminDTO);

    /**
     * 按id删除管理员
     *
     * @param id
     * @return java.lang.Boolean
     **/
    Boolean delete(Long id);

    /**
     * 按id查询管理员
     *
     * @param id
     * @return com.lwh147.rtms.backstage.pojo.dto.AdminDTO
     **/
    AdminDTO queryById(Long id);

    /**
     * 更新管理员，按dto中的id
     *
     * @param adminDTO
     * @return java.lang.Boolean
     **/
    Boolean update(AdminDTO adminDTO);

    /**
     * 查询管理员
     *
     * @param adminQuery 查询条件
     * @return java.util.List<com.lwh147.rtms.backstage.pojo.dto.AdminDTO>
     **/
    List<AdminDTO> commonQuery(AdminQuery adminQuery);
}
