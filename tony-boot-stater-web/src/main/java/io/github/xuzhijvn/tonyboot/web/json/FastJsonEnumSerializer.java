/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.web.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author tony
 * @create 2021-12-17
 * @description:
 * @// TODO: 2022/3/7 有性能问题 
 */
public class FastJsonEnumSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        serializer.getMapping().configEnumAsJavaBean((Class<? extends Enum>) object.getClass());
        serializer.write(object);
    }
}
