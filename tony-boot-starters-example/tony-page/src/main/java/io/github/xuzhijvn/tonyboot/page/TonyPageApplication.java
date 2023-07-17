package io.github.xuzhijvn.tonyboot.page;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tony.page.test.mapper")
public class TonyPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TonyPageApplication.class, args);
    }

}
