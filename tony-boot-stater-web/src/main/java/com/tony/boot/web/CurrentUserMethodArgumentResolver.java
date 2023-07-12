package com.tony.boot.web;


import com.tony.boot.tools.DefaultContext;
import com.tony.boot.web.annotation.CurrentUser;
import com.tony.boot.web.model.User;
import com.tony.boot.web.utils.AuthUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author tony
 * @ClassName: CurrentUserMethodArgumentResolver
 * @Description: 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * @date 2019年5月18日 下午3:59:17
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(CurrentUser.class))
                || parameter.hasMethodAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        User user = AuthUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException("annotation @CurrentUser or @DataPermission is used, but can't parse user from http header");
        }
        DefaultContext.set(user);
        return user;
    }
}
