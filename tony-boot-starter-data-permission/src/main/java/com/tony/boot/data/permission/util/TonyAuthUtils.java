package com.tony.boot.data.permission.util;

import cn.hutool.json.JSONUtil;
import com.tony.boot.data.permission.constant.AuthConstant;
import com.tony.boot.data.permission.domain.TonyUser;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class TonyAuthUtils {

    /**
     * 获取用户信息
     *
     * @return TonyUser
     */
    public static TonyUser getCurrentUser() {
        HttpServletRequest request = TonyWebUtils.getRequest();
        if (request == null) {
            return null;
        }
        try {
            String user = request.getHeader(AuthConstant.HEADER_USER);
            if (StringUtils.isEmpty(user)) {
                return null;
            }
            String userStr = URLDecoder.decode(user, "UTF-8");
            TonyUser TonyUser = JSONUtil.toBean(userStr, TonyUser.class);
            return TonyUser;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取租户Id
     *
     * @return tenantId
     */
    public static String getTenantId() {
        HttpServletRequest request = TonyWebUtils.getRequest();
        if (request == null) {
            return null;
        }
        try {
            String tenantId = request.getHeader(AuthConstant.TENANT_ID);
            String user = request.getHeader(AuthConstant.HEADER_USER);
            //如果请求头中的tenantId为空，那么尝试是否能够从登陆用户中去获取租户id
            if (StringUtils.isEmpty(tenantId) && !StringUtils.isEmpty(user)) {
                String userStr = URLDecoder.decode(user, "UTF-8");
                TonyUser TonyUser = JSONUtil.toBean(userStr, TonyUser.class);
                if (null != TonyUser) {
                    tenantId = TonyUser.getTenantId();
                }
            }
            return tenantId;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }
}
