/*
 *       Copyright© (2020).
 */
package com.tony.mybatis;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

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
            Object key2 = ReflectUtil.getFieldValue(e, keyField);
            if (ReflectUtil.getFieldValue(e, keyField).toString().equals(key.toString())) {
                return (E) e;
            }
        }
        return null;
    }

    public static <E extends Enum<E>> E toType(Class<E> clazz, Object key) {
        return toType(clazz, KEY_FIELD, key);
    }

    public void handle(Object t) {
        // 得到类的所有field.
        Field[] allFields = clazz.getDeclaredFields();
        for (int col = 0; col < allFields.length; col++) {
            Field field = allFields[col];
            BindEnumValue attr1 = field.getAnnotation(BindEnumValue.class);
            if (attr1 != null && attr1.enumClass() != Void.class && StrUtil.isNotBlank(attr1.key())) {
                String keyField = StrUtil.isBlank(attr1.keyField()) ? KEY_FIELD : attr1.keyField();
                String valueField = StrUtil.isBlank(attr1.valueField()) ? VALUE_FIELD : attr1.valueField();

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
            if (attr2 != null && StrUtil.isNotBlank(attr2.key())) {
                String keyField = StrUtil.isBlank(attr2.keyField()) ? KEY_FIELD : attr2.keyField();

                Object key = ReflectUtil.getFieldValue(t, attr2.key());

                // 设置类的私有字段属性可访问.
                field.setAccessible(true);

                try {
                    field.set(t, toType((Class<? extends Enum<?>>) field.getType(), keyField, key));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
