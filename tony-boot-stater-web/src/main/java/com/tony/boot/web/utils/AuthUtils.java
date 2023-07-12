package com.tony.boot.web.utils;

import cn.hutool.json.JSONUtil;
import com.tony.boot.tools.utils.StringUtils;
import com.tony.boot.web.constant.AuthConstant;
import com.tony.boot.web.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author tony
 * @create 2021/9/1
 * @description:
 */
public class AuthUtils {

    /**
     * 获取用户信息
     *
     * @return User
     */
    public static User getCurrentUser() {
        HttpServletRequest request = WebUtils.getRequest();
        if (request == null) {
            return null;
        }
        try {
            String user = request.getHeader(AuthConstant.HEADER_USER);
            if (StringUtils.isEmpty(user)) {
                return null;
            }
            String userStr = URLDecoder.decode(user, "UTF-8");
            return JSONUtil.toBean(userStr, User.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }
}
