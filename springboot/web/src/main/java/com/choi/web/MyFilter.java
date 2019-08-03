package com.choi.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author ChoiBin
 * @Date 2019-07-30 14:49
 * @Version 1.0
 */
@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("myfilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
