package org.king.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.king.utils.JwtUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;

        //1 获取请求路径
        String requestURI = request1.getRequestURI();
        //2 判断是否为登录路径  包含/login 放行

        if (requestURI.contains("/login")) {
            log.info("登录路径，放行");
            chain.doFilter(request, response);
            return;
        }

        //3 获取请求头中的Token
        String token = request1.getHeader("token");


        //4 判断是否存在，不存在是未登录，返回401
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401状态码");
            response1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5 存在，校验Token。失败-->返回错误信息（响应401状态码）
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.error("校验Token失败，响应401状态码", e);
            response1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6 校验通过，放行
        log.info("校验通过，放行");
        chain.doFilter(request, response);
    }
}
