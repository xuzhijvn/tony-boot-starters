package com.tony.component.test;

import cn.hutool.core.util.EnumUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ComponentTestApplicationTests {

    @Test
    void contextLoads() {
//        System.out.println(EnumUtil.getFieldValues(Job.class, "key"));
//        System.out.println(EnumUtil.getFieldValues(Job.class, "value"));
//        System.out.println(EnumUtil.getFieldNames(Job.class));
//        System.out.println(EnumUtil.getEnumMap(Job.class));
//        Map map = EnumUtil.getNameFieldMap(Job.class, "key");
//        System.out.println(map);
//        System.out.println(EnumUtil.getNameFieldMap(Job.class, "value"));
//        System.out.println(EnumUtil.fromString(Job.class, "XL_ANDROID"));
//        System.out.println(EnumUtil.getEnumAt(Job.class, 2));
//
//        System.out.println(BindEnumHandler.toValue(Job.class, "key", 1004, "value"));
//        Job job = BindEnumHandler.toType(Job.class, 1005);
//        System.out.println(job);

        User user = new User(1004);
        System.out.println(user);
    }

}
