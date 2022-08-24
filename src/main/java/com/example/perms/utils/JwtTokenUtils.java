package com.example.perms.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: 倪明辉
 * @date: 2019/3/6 15:11
 * @description: JWT工具类
 * JWT是由三段组成的，分别是header（头）、payload（负载）和signature（签名）
 * 其中header中放{
 *   "alg": "HS512",
 *   "typ": "JWT"
 * } 表明使用的加密算法，和token的类型==>默认是JWT
 *
 */
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    //密钥，用于signature（签名）部分解密
    private static final String PRIMARY_KEY = "adc@info";
    //签发者
    private static final String ISS = "SIRC";
    // 添加角色的key
    private static final String ROLE_CLAIMS = "role";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * description: 创建Token
     *
     * @param username
     * @param isRememberMe
     * @return java.lang.String
     */
    public static String createToken(String username, String id,List<String> roles, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, roles);
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        String compact = Jwts.builder()
                //采用HS512算法对JWT进行的签名,PRIMARY_KEY是我们的密钥
                .signWith(SignatureAlgorithm.HS512, PRIMARY_KEY)
                //设置角色名
                .setClaims(map)
                .setId(id)
                //设置发证人
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .compact();
        return TOKEN_PREFIX+compact;
    }

    /**
     * description: 从token中获取用户名
     *
     * @param token
     * @return java.lang.String
     */
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    // 获取用户角色
    public static List<String> getUserRole(String token) {
        return (List<String>) getTokenBody(token).get(ROLE_CLAIMS);
    }

    /**
     * description: 判断Token是否过期
     *
     * @param token
     * @return boolean
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * description:　获取
     *
     * @param token
     * @return io.jsonwebtoken.Claims
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(PRIMARY_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getId(String token){
        return getTokenBody(token).getId();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
}

