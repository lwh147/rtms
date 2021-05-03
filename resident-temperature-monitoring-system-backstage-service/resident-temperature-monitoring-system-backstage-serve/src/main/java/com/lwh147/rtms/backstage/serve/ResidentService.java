package com.lwh147.rtms.backstage.serve;

import com.lwh147.rtms.backstage.pojo.dto.ResidentDTO;
import com.lwh147.rtms.backstage.pojo.query.ResidentQuery;

import java.util.List;

/**
 * @description: 居民业务接口
 * @author: lwh
 * @create: 2021/5/3 12:17
 * @version: v1.0
 **/
public interface ResidentService {
    /**
     * 添加居民
     **/
    Boolean add(ResidentDTO residentDTO);

    /**
     * 按id删除居民
     **/
    Boolean delete(Long id);

    /**
     * 按id查询居民
     **/
    ResidentDTO queryById(Long id);

    /**
     * 更新居民，按dto中的id
     **/
    Boolean update(ResidentDTO residentDTO);

    /**
     * 查询居民
     **/
    List<ResidentDTO> commonQuery(ResidentQuery residentQuery);
}
