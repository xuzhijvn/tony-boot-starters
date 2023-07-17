package io.github.xuzhijvn.tonyboot.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.junit.jupiter.api.Test;

class TonyMybatisApplicationTests {

    @Test
    void contextLoads() {
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(Job.class);
        String s = JSONObject.toJSONString(Job.AS, config);
        System.out.println(s);
    }

}
