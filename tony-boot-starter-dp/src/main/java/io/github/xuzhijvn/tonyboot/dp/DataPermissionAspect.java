/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp;


import io.github.xuzhijvn.tonyboot.dp.annotation.DataConvert;
import io.github.xuzhijvn.tonyboot.dp.annotation.DataPermission;
import io.github.xuzhijvn.tonyboot.dp.converter.IDataConverter;
import io.github.xuzhijvn.tonyboot.dp.converter.IUserConverter;
import io.github.xuzhijvn.tonyboot.dp.handler.IPermissionHandler;
import io.github.xuzhijvn.tonyboot.dp.model.Permission;
import io.github.xuzhijvn.tonyboot.tools.DefaultContext;
import io.github.xuzhijvn.tonyboot.tools.exception.TonyException;
import io.github.xuzhijvn.tonyboot.web.model.User;
import io.github.xuzhijvn.tonyboot.web.utils.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author tony
 * @create 2021-09-02
 * @description:
 */
@Aspect
//@Order(-1)
public class DataPermissionAspect {

    private final IUserConverter userConverter;

    public DataPermissionAspect(IUserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Before(value = "@annotation(dataPermission)")
    public void doBefore(JoinPoint joinPoint, DataPermission dataPermission) throws Throwable {

        User currentUser = (User) DefaultContext.get(User.class);

        Assert.isTrue(currentUser != null && currentUser.getId() != null,
                "when @DataPermission is configured, the User in the header cannot be empty");

        Object[] params = joinPoint.getArgs();

        Assert.isTrue(params.length != 0,
                "@DataPermission can't be used in no args method, " + joinPoint.getSignature().getName());

        Class<?> dataConvertUsing = dataPermission.dataConvertUsing();

        Assert.isTrue(dataConvertUsing != Void.class,
                "dataConvertUsing must be configure in @DataPermission");

        //获取dataConverter
        IDataConverter dataConverter = (IDataConverter) getInstance(dataConvertUsing);

        Object convertData = getAnnotationParameter(joinPoint, DataConvert.class);

        Assert.notNull(convertData,
                "@DataConvert must be configure at least one to the method parameter when the method be annotated by @DataPermission");

        Permission permission = dataConverter.convert(convertData);

        Class<?> permissionHandlerUsing = dataPermission.permissionHandlerUsing();

        //获取permissionHandler
        IPermissionHandler permissionHandler = (IPermissionHandler) getInstance(permissionHandlerUsing);

        Set<Permission> userPermissions = userConverter.convert(currentUser);

        if (!permissionHandler.hasPermission(userPermissions, permission)) {
            LogUtils.ACCESS_LOG.warn("The current user does not have data permissions, " + currentUser + ", " + permission);
            throw new TonyException("没有数据权限");
        }

    }

    private Object getInstance(Class<?> clazz) throws Exception {
        Object object = null;
        try {
            object = ApplicationContextHolder.getBean(clazz);
        } catch (NoSuchBeanDefinitionException e) {
        }
        if (object == null) {
            return clazz.getDeclaredConstructor().newInstance();
        }
        return object;
    }

    public Object getAnnotationParameter(JoinPoint joinPoint, Class<? extends Annotation> annotationType) {
        //获取方法，此处可将signature强转为MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[][] annotations = method.getParameterAnnotations();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            for (Annotation annotation : annotations[i]) {
                if (annotation.annotationType().equals(annotationType)) {
                    return args[i];
                }
            }
        }
        return null;
    }
}
