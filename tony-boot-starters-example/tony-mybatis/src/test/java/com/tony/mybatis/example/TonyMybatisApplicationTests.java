package com.tony.mybatis.example;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class TonyMybatisApplicationTests {

    @Test
    void contextLoads() {
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(Job.class);
        String s = JSONObject.toJSONString(Job.AS, config);
        System.out.println(s);
    }

}
