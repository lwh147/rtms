package com.lwh147.rtms.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @description: 认证鉴权全局过滤器
 * @author: lwh
 * @create: 2021/7/21 11:33
 * @version: v1.0
 **/
@Configuration
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Resource
    private RedisUtils redisUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        log.info("请求地址：{}", path);
        // 登录请求无需鉴权
        if (antPathMatcher.match("/admin/loginFromApp", path)
                || antPathMatcher.match("/admin/loginFromBackstage", path)) {
            return chain.filter(exchange);
        }

        // 获取token
        String token = "";
        List<String> tokenList = request.getHeaders().get("X-Token");

        // token是否为空
        if (Objects.isNull(tokenList)) {
            ServerHttpResponse response = exchange.getResponse();
            return out(response, CommonExceptionCodeImpl.COMMON_NOT_LOGIN_ERROR);
        }


        // 鉴权
        token = tokenList.get(0);
        log.info("获取到的token：{}", token);
        Object object = redisUtils.get(token);
        if (Objects.isNull(object)) {
            ServerHttpResponse response = exchange.getResponse();
            return out(response, CommonExceptionCodeImpl.COMMON_AUTH_ERROR);
        }
        log.info("根据token获取到的用户信息：{}", JSON.toJSONString(object));
        return chain.filter(exchange);
    }

    /**
     * api接口鉴权失败返回数据
     *
     * @param response
     * @return
     */
    private Mono<Void> out(ServerHttpResponse response, CommonExceptionCode commonExceptionCode) {
        CommonResponse<?> commonResponse = CommonResponseUtil.failed(commonExceptionCode);
        byte[] bits = JSONObject.toJSONString(commonResponse).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
