package com.lwh147.rtms.gateway.filter;

/**
 * @description: 生成返回结果的工具类
 * @author: lwh
 * @create: 2021/4/30 23:59
 * @version: v1.0
 **/
public class CommonResponseUtil {
    public static final Integer COMMON_RESPONSE_CODE_SUCCESS = 0;
    public static final String COMMON_RESPONSE_MESSAGE_SUCCESS = "操作成功";
    public static final Integer COMMON_RESPONSE_CODE_FAILED_DEFAULT = -1;
    public static final String COMMON_RESPONSE_MESSAGE_FAILED_DEFAULT = "操作失败";

    /**
     * 只包含静态方法，不能实例化对象
     **/
    private CommonResponseUtil() {
    }

    /**
     * 构建含有返回数据的成功应答
     *
     * @param data 要返回的数据
     * @return CommonResponse<T>
     **/
    public static <T> CommonResponse<T> success(T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setData(data);
        commonResponse.setCode(COMMON_RESPONSE_CODE_SUCCESS);
        commonResponse.setMessage(COMMON_RESPONSE_MESSAGE_SUCCESS);
        return commonResponse;
    }

    /**
     * 构建无返回数据的成功应答
     *
     * @return CommonResponse<T>
     **/
    public static <T> CommonResponse<T> success() {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setCode(COMMON_RESPONSE_CODE_SUCCESS);
        commonResponse.setMessage(COMMON_RESPONSE_MESSAGE_SUCCESS);
        return commonResponse;
    }

    /**
     * 构建默认失败应答
     *
     * @return CommonResponse<T>
     **/
    public static <T> CommonResponse<T> failed() {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setCode(COMMON_RESPONSE_CODE_FAILED_DEFAULT);
        commonResponse.setMessage(COMMON_RESPONSE_MESSAGE_FAILED_DEFAULT);
        return commonResponse;
    }

    /**
     * 构建包含错误信息的失败应答
     *
     * @param message 错误消息
     * @return com.lwh147.rtms.backstage.config.common.response.CommonResponse<T>
     **/
    public static <T> CommonResponse<T> failed(String message) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setCode(COMMON_RESPONSE_CODE_FAILED_DEFAULT);
        commonResponse.setMessage(message);
        return commonResponse;
    }

    /**
     * 构建包含错误码和错误信息的失败应答
     *
     * @param code    错误码
     * @param message 错误消息
     * @return com.lwh147.rtms.backstage.config.common.response.CommonResponse<T>
     **/
    public static <T> CommonResponse<T> failed(Integer code, String message) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        return commonResponse;
    }

    /**
     * 根据全剧异常码构建失败应答
     *
     * @param commonExceptionCode 全局异常枚举对象
     * @return com.lwh147.rtms.backstage.common.response.CommonResponse<T>
     **/
    public static <T> CommonResponse<T> failed(CommonExceptionCode commonExceptionCode) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setCode(commonExceptionCode.getCode());
        commonResponse.setMessage(commonExceptionCode.getMessage());
        return commonResponse;
    }

}
