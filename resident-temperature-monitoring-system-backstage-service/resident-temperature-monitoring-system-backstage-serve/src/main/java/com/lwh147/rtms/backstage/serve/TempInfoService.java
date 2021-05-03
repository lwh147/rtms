package com.lwh147.rtms.backstage.serve;

import com.lwh147.rtms.backstage.pojo.dto.TempInfoDTO;
import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;

import java.util.List;

/**
 * @description: 体温信息业务接口
 * @author: lwh
 * @create: 2021/5/3 21:16
 * @version: v1.0
 **/
public interface TempInfoService {
    /**
     * 添加体温信息
     **/
    Boolean add(TempInfoDTO tempInfoDTO);

    /**
     * 查询体温信息
     **/
    List<TempInfoDTO> commonQuery(TempInfoQuery tempInfoQuery);
}
