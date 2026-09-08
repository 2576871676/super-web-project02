package org.king.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.king.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*//1 获取请求路径
        String requestURI = request.getRequestURI();
        //2 判断是否为登录路径  包含/login 放行

        if (requestURI.contains("/login")) {
            log.info("登录路径，放行");
            return true;
        }*/

        //3 获取请求头中的Token
        String token = request.getHeader("token");


        //4 判断是否存在，不存在是未登录，返回401
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5 存在，校验Token。失败-->返回错误信息（响应401状态码）
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.error("校验Token失败，响应401状态码", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //6 校验通过，放行
        log.info("校验通过，放行");
        return true;
    }
}
