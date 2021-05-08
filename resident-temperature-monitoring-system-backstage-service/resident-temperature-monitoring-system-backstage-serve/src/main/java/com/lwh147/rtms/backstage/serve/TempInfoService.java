package com.lwh147.rtms.backstage.serve;

import com.lwh147.rtms.backstage.pojo.dto.TempInfoDTO;
import com.lwh147.rtms.backstage.pojo.query.TempInfoQuery;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询监测总人数
     **/
    Integer getTotal(TempInfoQuery tempInfoQuery);

    /**
     * 查询图表数据
     **/
    List<Map<String, Object>> getTemp(TempInfoQuery tempInfoQuery);

    /**
     * 查询某个人的体温数据
     **/
    List<Map<String, Object>> getTempByResidentId(TempInfoQuery tempInfoQuery);

    /**
     * 获取15天内所有记录
     **/
    List<Map<String, Object>> getTempOf15();
}
