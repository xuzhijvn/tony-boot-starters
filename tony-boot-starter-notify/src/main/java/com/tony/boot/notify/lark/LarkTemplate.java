/*
 *       Copyright© (2020) Lalamove Co., Ltd.
 */
package com.tony.boot.notify.lark;


import com.tony.boot.notify.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author tony
 * @create 2021-07-19
 * @description:
 */

public class LarkTemplate implements Notifier<LarkRequest> {

    private static final Logger log = LoggerFactory.getLogger(LarkTemplate.class);

    private final LarkProperties larkProperties;

    private final RestTemplate restTemplate;

    public LarkTemplate(LarkProperties larkProperties, RestTemplate restTemplate) {
        this.larkProperties = larkProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void notify(String message) {
        LarkRequest request = LarkRequest.build(null, message, null);
        String response = null;
        try {
            response = restTemplate.postForObject(larkProperties.getWebhooks(), request, String.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            log.info("request = {}, response = {}", request, response);
        }
    }

    @Override
    public void notify(LarkRequest larkRequest) {
        String response = null;
        try {
            response = restTemplate.postForObject(larkProperties.getWebhooks(), larkRequest, String.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            log.info("request = {}, response = {}", larkRequest, response);
        }
    }
}
