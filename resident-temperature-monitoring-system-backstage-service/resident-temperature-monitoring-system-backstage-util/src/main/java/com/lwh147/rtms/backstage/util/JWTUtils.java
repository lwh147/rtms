package com.lwh147.rtms.backstage.util;

import io.jsonwebtoken.*;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: jwt  token生成工具类
 * @author: lwh
 * @create: 2021/7/21 11:06
 * @version: v1.0
 **/
public class JWTUtils {
    /**
     * token有效时长
     **/
    public final static long EXPIRE = 15 * 60 * 1000;
    /**
     * token签名私钥
     **/
    public final static String SECRET = "rtms_token_secret_lwh147";
    /**
     * token名称
     **/
    public final static String SUBJECT = "RTMS_TOKEN";

    private JWTUtils() {
    }

    public static String createToken() {
        return Jwts.builder()
                .setSubject(SUBJECT)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 根据payload生成token
     *
     * @param payload
     * @return java.lang.String
     **/
    public static String createToken(Map<String, Object> payload) {
        Assert.notNull(payload, "payload不能为空");
        return Jwts.builder()
                .setSubject(SUBJECT)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compressWith(CompressionCodecs.GZIP)
                .setClaims(payload)
                .compact();
    }

    /**
     * 根据id生成token
     *
     * @param id
     * @return java.lang.String
     **/
    public static String createToken(Long id) {
        Assert.notNull(id, "id不能为空");
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return createToken(map);
    }

    /**
     * 获取token中的id
     *
     * @param token
     * @return java.lang.Long
     **/
    public static Long getId(String token) {
        Assert.notNull(token, "token不能为空");
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return ((Integer) claims.get("id")).longValue();
    }

    /**
     * 获取token中名称为name的值
     *
     * @param token
     * @param name
     * @return java.lang.Object
     **/
    public static Object get(String token, String name) {
        Assert.notNull(token, "token不能为空");
        Assert.notNull(name, "name不能为空");
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get(name);
    }

    public static void main(String[] args) {
        String token = JWTUtils.createToken(123456L);
        System.out.println("生成的token：" + token);
        System.out.println("获取id: " + JWTUtils.getId(token));
    }
}
