package com.tony.dp;

import com.tony.dp.model.User;
import com.tony.dp.util.TonyAuthUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author tony
 * @create 2021/7/29
 * @description:
 */
public class DataPermissionFilter implements Filter {

    private Boolean isEnableCurrentUser;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        this.isEnableCurrentUser = Boolean.parseBoolean(filterConfig.getInitParameter("current-user"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User currentUser = TonyAuthUtils.getCurrentUser();
        if (isEnableCurrentUser && ObjectUtils.isEmpty(currentUser)) {
            throw new TonyException(-1, "tony dp current-user is enable, but can't parse user from http header");
        }
        TonyContext.set(currentUser);
        filterChain.doFilter(servletRequest, servletResponse);
        // 执行完后清除缓存
        TonyContext.removeCache();
    }
}