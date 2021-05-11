package com.lwh147.rtms.backstage.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: TempInfoWithFace
 * @author: lwh
 * @create: 2021/5/11 14:41
 * @version: v1.0
 **/
@Data
@ApiModel(description = "TempInfoWithFaceVO")
@NoArgsConstructor
@AllArgsConstructor
public class TempInfoWithFaceVO {
    /**
     * 检测时间
     */
    @ApiModelProperty("检测时间，格式：yyyy/MM/dd HH:mm:ss")
    private Date time;

    /**
     * 检测结果，温度摄氏度
     */
    @ApiModelProperty("检测结果，温度摄氏度，只保留一位小数")
    private Float temp;

    /**
     * base64编码的图片字符串
     */
    @ApiModelProperty("base64编码的字符串")
    private String picture;
}
