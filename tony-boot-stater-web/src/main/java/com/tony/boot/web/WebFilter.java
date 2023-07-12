package com.tony.boot.web;

import com.tony.boot.tools.DefaultContext;
import com.tony.boot.tools.exception.TonyException;
import com.tony.boot.web.model.User;
import com.tony.boot.web.utils.AuthUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author tony
 * @create 2021/7/29
 * @description:
 */
public class WebFilter implements Filter {

    private Boolean isEnableCurrentUser;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        this.isEnableCurrentUser = Boolean.parseBoolean(filterConfig.getInitParameter("current-user"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User currentUser = AuthUtils.getCurrentUser();
        if (isEnableCurrentUser && ObjectUtils.isEmpty(currentUser)) {
            throw new TonyException(-1, "tony-common 'current-user' is enable, but can't parse user from http header");
        }
        DefaultContext.set(currentUser);
        filterChain.doFilter(servletRequest, servletResponse);
        // 执行完后清除缓存
        DefaultContext.removeCache();
    }
}