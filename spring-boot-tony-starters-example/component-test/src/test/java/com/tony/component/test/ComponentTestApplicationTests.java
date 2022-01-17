package com.tony.component.test;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import com.tony.component.template.LarkTemplate;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

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
        //System.out.println(user);


        boolean f = ReUtil.contains("com.huolala.risk.*", "com.huolala.risk.tony.MyException");

        String amount = "";

//        Integer a = amount == null ? null : (int) (Double.parseDouble(amount) * 100);
//
//        System.out.println(a);

        Method method = ReflectUtil.getMethodByName(LarkTemplate.class, "getLogUrl");

//        System.out.println(method.getName());
//        System.out.println(method.getDeclaringClass());
//        System.out.println(method.getDeclaringClass().getName());

        String a = "f";
        String b = "";

        boolean hitHandleType = Arrays.stream(a.split(",")).anyMatch(e -> e.equals(b));

        //System.out.println(hitHandleType);

        Integer c = null;

        Integer payType = Optional.ofNullable(c).map(e -> e == 0 ? 0 : 1).orElse(null);

        //System.out.println(payType);

        String str = "";
        String i = "";

        System.out.println(str.split(",").length);

        boolean res = Arrays.asList(str.split(",")).contains(i);

        System.out.println(res);


    }

}
