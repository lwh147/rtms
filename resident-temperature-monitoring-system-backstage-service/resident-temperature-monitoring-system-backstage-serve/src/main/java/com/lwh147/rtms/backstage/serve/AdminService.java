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
     **/
    Boolean add(AdminDTO adminDTO);

    /**
     * 按id删除管理员
     **/
    Boolean delete(Long id);

    /**
     * 按id查询管理员
     **/
    AdminDTO queryById(Long id);

    /**
     * 更新管理员，按dto中的id
     **/
    Boolean update(AdminDTO adminDTO);

    /**
     * 查询管理员
     **/
    List<AdminDTO> commonQuery(AdminQuery adminQuery);

    /**
     * 登录
     **/
    AdminDTO loginFromApp(AdminQuery adminQuery);
}
