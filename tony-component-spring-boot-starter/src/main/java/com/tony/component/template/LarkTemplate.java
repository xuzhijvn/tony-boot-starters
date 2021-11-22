/*
 *       Copyright© (2020) Lalamove Co., Ltd.
 */
package com.tony.component.template;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tony.common.ExpiryMap;
import com.tony.component.GlobalDefaultProperties;
import com.tony.component.model.LarkPostRequest;
import com.tony.component.util.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author tony
 * @create 2021-07-19
 * @description:
 */

public class LarkTemplate {

    private static final Logger log = LoggerFactory.getLogger(LarkTemplate.class);

    private final ExpiryMap<Object, Object> expiryMap;


    private GlobalDefaultProperties globalDefaultProperties;

    public LarkTemplate(GlobalDefaultProperties globalDefaultProperties) {
        this.globalDefaultProperties = globalDefaultProperties;
        this.expiryMap = new ExpiryMap<>(1000 * 10);
    }

    public GlobalDefaultProperties getGlobalDefaultProperties() {
        return globalDefaultProperties;
    }

    public void setGlobalDefaultProperties(GlobalDefaultProperties globalDefaultProperties) {
        this.globalDefaultProperties = globalDefaultProperties;
    }

    private String getLogUrl(final String env) {
        String logEnv = env;
        if ("pre".equals(env)) {
            logEnv = "pre2";
        }
        if ("未知".equals(env)) {
            logEnv = "stg";
        }
        Instant now = Instant.now();
        Instant now1 = now.minusSeconds(2);
        Instant now2 = now.plusSeconds(10);
        String url = Optional.ofNullable(this.globalDefaultProperties.getLark().getKibana().getUrl()).orElse("''");
        String sname = Optional.ofNullable(this.globalDefaultProperties.getLark().getKibana().getSname()).orElse("''");
        String stype = Optional.ofNullable(this.globalDefaultProperties.getLark().getKibana().getStype()).orElse("''");
        String hllTid = MDC.get("HLL_TID");
        if (hllTid == null) {
            hllTid = "''";
        } else {
            hllTid = "%22" + hllTid + "%22";
        }
        return url + "?_g=(filters:!(\\),refreshInterval:(pause:!t,value:0\\)," +
                "time:(from:'" + now1 + "',to:'" + now2 + "'\\)\\)" +
                "&_a=(columns:!(message,request\\),filters:!(\\),interval:auto,query:(language:kuery,query:" + hllTid + "\\)," +
                "senv:" + logEnv + ",sname:" + sname + ",sort:!(\\),stype:" + stype + "\\)";
    }

    public void send(String titleName, Object content, String ex, String color) {

        try {
            content = "**环境** 👉 " + getEnv() + "\n ---\n" + content + "\n ---\n**Exception** 👉 " + ex + "\n ---\n[**日志传送门🚪**](" + getLogUrl(getEnv()) + ")\n";
            List<LarkPostRequest.Card.Element> elements = new ArrayList<>();
            LarkPostRequest.Card.Element element = new LarkPostRequest.Card.Element();
            element.setContent(content.toString());
            elements.add(element);
            LarkPostRequest.Card card = new LarkPostRequest.Card();
            card.setElements(elements);
            LarkPostRequest.Card.Header header = new LarkPostRequest.Card.Header();
            LarkPostRequest.Card.Header.Title title = new LarkPostRequest.Card.Header.Title();
            title.setContent(titleName);
            header.setTitle(title);
            header.setTemplate(color != null ? color : header.getTemplate());
            card.setHeader(header);
            LarkPostRequest larkPostRequest = new LarkPostRequest();
            larkPostRequest.setCard(card);

            HttpClient.post(this.globalDefaultProperties.getLark().getWebhooks(), (JSONObject) JSON.toJSON(larkPostRequest));
        } catch (Exception e) {
            log.error("发生飞书通知失败", e);
        }
    }

    public void send(String titleName, Object content, String ex) {
        send(titleName, content, ex, null);
    }

    public void sendAsync(String titleName, Object content, String ex) {
        // 提交者的本地变量
        Map<String, String> contextMap = MDC.getCopyOfContextMap();

        CompletableFuture.runAsync(() -> {
                    if (contextMap != null) {
                        // 如果提交者有本地变量，任务执行之前放入当前任务所在的线程的本地变量中
                        MDC.setContextMap(contextMap);
                    }
                    send(titleName, content, ex);
                    // 任务执行完，清除本地变量，以防对后续任务有影响
                    MDC.clear();
                }
        );
    }

    public void sendIfAbsent(String titleName, Object content, String ex) {
        String key = MDC.get("HLL_TID");
        if (key == null) {
            key = content.toString();
        }
        if (!expiryMap.containsKey(key)) {
            expiryMap.put(key, 1);
            sendAsync(titleName, content, ex);
        }
    }

    private String getEnv() {
        String env = System.getProperty("hll.env");
        if (env == null) {
            env = System.getProperty("spring.profiles.active");
        }
        return env != null ? env : "未知";
    }
}
