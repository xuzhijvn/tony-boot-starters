package com.tony.component.filter;

import com.tony.component.context.ThreadLocalCacheManager;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author tony
 * @create 2021/7/29
 * @description:
 */
public class ThreadLocalCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        filterChain.doFilter(servletRequest, servletResponse);
        // 执行完后清除缓存
        ThreadLocalCacheManager.removeCache();
        System.out.println("清除ThreadLocal");
    }
}