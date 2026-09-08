package org.king.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * JWT 令牌操作工具类
 */
public class JwtUtils {

    /** 签名密钥（与测试类保持一致，必须 >= 32 字节） */
    private static final String SIGN_KEY = "itsuper1234567890123456789012345678";

    /** 令牌过期时间：30 分钟 */
    private static final long EXPIRE_TIME = 30 * 60 * 1000L;

    private JwtUtils() {
    }

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义载荷数据
     * @return JWT 令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        SecretKey key = Keys.hmacShaKeyFor(SIGN_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .signWith(key)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .compact();
    }

    /**
     * 解析 JWT 令牌，获取载荷
     *
     * @param jwt JWT 令牌字符串
     * @return 载荷 Claims
     */
    public static Claims parseJwt(String jwt) {
        SecretKey key = Keys.hmacShaKeyFor(SIGN_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
