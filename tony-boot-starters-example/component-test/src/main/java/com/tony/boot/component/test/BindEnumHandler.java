/*
 *       Copyright© (2020).
 */
package com.tony.boot.component.test;

import cn.hutool.core.util.ReflectUtil;
import com.tony.boot.tools.utils.StringUtils;

import java.lang.reflect.Field;

/**
 * @author tony
 * @create 2021-11-25
 * @description:
 */
public class BindEnumHandler<T> {

    private final static String KEY_FIELD = "key";
    private final static String VALUE_FIELD = "value";

    /**
     * 实体对象
     */
    public Class<T> clazz;

    public BindEnumHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void handle(T t) {
        // 得到类的所有field.
        Field[] allFields = clazz.getDeclaredFields();
        for (int col = 0; col < allFields.length; col++) {
            Field field = allFields[col];
            BindEnumValue attr1 = field.getAnnotation(BindEnumValue.class);
            if (attr1 != null && attr1.enumClass() != Void.class && StringUtils.isNotBlank(attr1.key())) {
                String keyField = StringUtils.isBlank(attr1.keyField()) ? KEY_FIELD : attr1.keyField();
                String valueField = StringUtils.isBlank(attr1.valueField()) ? VALUE_FIELD : attr1.valueField();

                Object key = ReflectUtil.getFieldValue(t, attr1.key());

                String textValue = toValue((Class<? extends Enum<?>>) attr1.enumClass(), keyField, key, valueField);

                // 设置类的私有字段属性可访问.
                field.setAccessible(true);
                try {
                    field.set(t, textValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            BindEnum attr2 = field.getAnnotation(BindEnum.class);
            if (attr2 != null && attr2.enumClass() != Void.class && StringUtils.isNotBlank(attr2.key())) {
                String keyField = StringUtils.isBlank(attr2.keyField()) ? KEY_FIELD : attr2.keyField();

                Object key = ReflectUtil.getFieldValue(t, attr2.key());

                // 设置类的私有字段属性可访问.
                field.setAccessible(true);

                try {
                    field.set(t, toType((Class<? extends Enum<?>>) attr2.enumClass(), keyField, key));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static <E extends Enum<E>> String toValue(Class<? extends Enum<?>> clazz, String keyField, Object key, String valueField) {
        Enum<?> e = toType(clazz, keyField, key);
        if (e == null) {
            return null;
        }
        return ReflectUtil.getFieldValue(e, valueField).toString();
    }

    public static <E extends Enum<E>> String toValue(Class<E> clazz, Object key) {
        return toValue(clazz, KEY_FIELD, key, VALUE_FIELD);
    }

    public static <E extends Enum<E>> E toType(Class<? extends Enum<?>> clazz, String keyField, Object key) {
        final Enum<?>[] enums = clazz.getEnumConstants();
        if (null == enums) {
            return null;
        }
        for (Enum<?> e : enums) {
            if (ReflectUtil.getFieldValue(e, keyField).equals(key)) {
                return (E) e;
            }
        }
        return null;
    }

    public static <E extends Enum<E>> E toType(Class<E> clazz, Object key) {
        return toType(clazz, KEY_FIELD, key);
    }


}
