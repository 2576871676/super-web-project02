package org.king.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


// @WebFilter(urlPatterns = "/*") //拦截所有请求
@Slf4j
public class DemoFilter implements Filter {

    //初始化方法，启动时执行，一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法执行 ...");
    }

    //拦截到请求执行，执行多次
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain Chain) throws IOException, ServletException {
        log.info("拦截到请求...");
        //放行
        Chain.doFilter(request,response);
    }

    //销毁方法，关闭时执行一次
    @Override
    public void destroy() {
        log.info("destroy 销毁方法执行了 ...");
    }
}

