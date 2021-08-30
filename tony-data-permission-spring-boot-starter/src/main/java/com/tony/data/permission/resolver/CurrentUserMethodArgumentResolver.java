package com.tony.data.permission.resolver;


import com.tony.data.permission.annotation.auth.CurrentUser;
import com.tony.data.permission.domain.GitEggUser;
import com.tony.data.permission.util.GitEggAuthUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @ClassName: CurrentUserMethodArgumentResolver
 * @Description: 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * @author GitEgg
 * @date 2019年5月18日 下午3:59:17
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(GitEggUser.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        GitEggUser user = GitEggAuthUtils.getCurrentUser();
        return user;
    }
}
