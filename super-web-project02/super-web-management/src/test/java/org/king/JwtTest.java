package org.king;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 测试生成JWT，并解析出payload以JSON格式输出到控制台
     */
    @Test
    public void testGenerateJwt() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        // 密钥必须 >= 32 字节（256 bits），HS256 才会通过校验
        SecretKey key = Keys.hmacShaKeyFor(
                "itsuper1234567890123456789012345678".getBytes(StandardCharsets.UTF_8));

        // 1. 生成JWT
        String jwt = Jwts.builder()
                .signWith(key)                    // 0.12.x：直接传 SecretKey
                .claims(dataMap)                  // 自定义信息
                .expiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5分钟过期
                .compact();
        System.out.println("生成的JWT: " + jwt);

        // 2. 解析JWT，取出payload
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        // 3. 将payload序列化为JSON并输出
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(claims);
        System.out.println("payload JSON: " + json);
    }
}
