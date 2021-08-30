package com.tony.data.permission.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tony.data.permission.domain.TonyUser;
import com.tony.data.permission.util.TonyAuthUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName: MyMetaObjectHandler
 * @Description: 自定义填充公共字段
 * @author tony
 * @date 2019年5月19日 上午10:32:29
 */
@Component
public class TonyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object creator = getFieldValByName("creator", metaObject);
        TonyUser TonyUser = TonyAuthUtils.getCurrentUser();
        if (null == creator && null != TonyUser) {
           setFieldValByName("creator", TonyUser.getId(), metaObject);
        }
        Object createTime = getFieldValByName("createTime", metaObject);
        if (null == createTime) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object operator = getFieldValByName("operator", metaObject);
        TonyUser TonyUser = TonyAuthUtils.getCurrentUser();
        if (null == operator && null != TonyUser) {
            setFieldValByName("operator", TonyUser.getId(), metaObject);
        }
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (null == updateTime) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
