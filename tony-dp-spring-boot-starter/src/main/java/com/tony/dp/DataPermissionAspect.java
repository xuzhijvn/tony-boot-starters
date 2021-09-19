/*
 *       Copyright© (2020).
 */
package com.tony.dp;


import com.tony.dp.annotation.DataConvert;
import com.tony.dp.annotation.DataPermission;
import com.tony.dp.converter.IDataConverter;
import com.tony.dp.converter.IUserConverter;
import com.tony.dp.handler.IPermissionHandler;
import com.tony.dp.model.Permission;
import com.tony.dp.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author tony
 * @create 2021-09-02
 * @description:
 */
@Aspect
@Component
public class DataPermissionAspect {

    @Resource
    IUserConverter userConverter;

    @Before(value = "@annotation(dataPermission)")
    public void doBefore(JoinPoint joinPoint, DataPermission dataPermission) throws Throwable {
        Object[] params = joinPoint.getArgs();
        if (params.length == 0) {
            throw new RuntimeException("@DataPermission can't be used in no args method, " + joinPoint.getSignature().getName());
        }
        Class<?> dataConvertUsing = dataPermission.dataConvertUsing();
        if (dataConvertUsing == Void.class) {
            throw new RuntimeException("dataConvertUsing must be configure in @DataPermission");
        }
        IDataConverter dataConverter = (IDataConverter) dataConvertUsing.getDeclaredConstructor().newInstance();
        //IDataConverter dataConverter = (IDataConverter) BeanUtil.getBean(dataConvertUsing);

        Object convertData = getAnnotationParameter(joinPoint, DataConvert.class);
        if (convertData == null) {
            throw new RuntimeException("@DataConvert must be configure at least one to the method parameter when the method be annotated by @DataPermission");
        }
        Permission permission = dataConverter.convert(convertData);
        User currentUser = (User) TonyContext.get(User.class);

        Class<?> permissionHandlerUsing = dataPermission.permissionHandlerUsing();
        //IPermissionHandler permissionHandler = (IPermissionHandler) BeanUtil.getBean(permissionHandlerUsing);
        IPermissionHandler permissionHandler = (IPermissionHandler) permissionHandlerUsing.getDeclaredConstructor().newInstance();
        Set<Permission> userPermissions = userConverter.convert(currentUser);
        if (!permissionHandler.hasPermission(userPermissions, permission)) {
            throw new RuntimeException("The current user does not have data permissions, " + currentUser + ", " + permission);
        }
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
