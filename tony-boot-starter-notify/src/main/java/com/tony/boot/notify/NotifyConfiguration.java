/*
 *       Copyright© (2020).
 */
package com.tony.boot.notify;


import com.tony.boot.notify.lark.LarkAspect;
import com.tony.boot.notify.lark.LarkProperties;
import com.tony.boot.notify.lark.LarkTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author tony
 * @create 2021-07-28
 * @description:
 */
@Configuration
@EnableConfigurationProperties(LarkProperties.class)
public class NotifyConfiguration {


    @Bean
    @ConditionalOnMissingBean(LarkAspect.class)
    public LarkAspect larkAspect() {
        return new LarkAspect();
    }

    @Bean
    @ConditionalOnMissingBean(LarkTemplate.class)
    @ConditionalOnProperty(prefix = "tony.notify.lark", name = "webhooks")
    public LarkTemplate larkTemplate(LarkProperties larkProperties,
                                     @Qualifier(value = "tonyRestTemplate") RestTemplate restTemplate,
                                     ObjectProvider<LarkCustomizer> customizers) {
        LarkTemplate larkTemplate = new LarkTemplate(larkProperties, restTemplate);
        customizers.orderedStream().forEach(customizer -> customizer.customize(larkTemplate));
        return larkTemplate;
    }

    @Bean(name = "tonyRestTemplate")
    public RestTemplate restTemplate(@Qualifier(value = "tonySimpleClientHttpRequestFactory")
                                     ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean(name = "tonySimpleClientHttpRequestFactory")
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(1000);
        return factory;
    }
}
