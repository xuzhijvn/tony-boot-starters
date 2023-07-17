package io.github.xuzhijvn.tonyboot.web.utils;

import cn.hutool.json.JSONUtil;
import io.github.xuzhijvn.tonyboot.tools.utils.StringUtils;
import io.github.xuzhijvn.tonyboot.web.constant.AuthConstant;
import io.github.xuzhijvn.tonyboot.web.model.User;

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
