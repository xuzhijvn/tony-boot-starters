/*
 *       Copyright© (2020) Lalamove Co., Ltd.
 */
package com.tony.component.template;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tony.component.GlobalDefaultProperties;
import com.tony.component.model.FeishuPostRequest;
import com.tony.component.util.HttpClient;
import org.slf4j.MDC;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tony
 * @create 2021-07-19
 * @description:
 */
public class FeishuTemplate {


    private GlobalDefaultProperties globalDefaultProperties;

    public FeishuTemplate(GlobalDefaultProperties globalDefaultProperties) {
        this.globalDefaultProperties = globalDefaultProperties;
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
        String url = Optional.ofNullable(this.globalDefaultProperties.getFeishu().getKibana().getUrl()).orElse("''");
        String sname = Optional.ofNullable(this.globalDefaultProperties.getFeishu().getKibana().getSname()).orElse("''");
        String stype = Optional.ofNullable(this.globalDefaultProperties.getFeishu().getKibana().getStype()).orElse("''");
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

        content = "**环境** 👉 " + getEnv() + "\n ---\n" + content + "\n ---\n**Exception** 👉 " + ex + "\n ---\n[**日志传送门**](" + getLogUrl(getEnv()) + ")\n";
        List<FeishuPostRequest.Card.Element> elements = new ArrayList<>();
        FeishuPostRequest.Card.Element element = new FeishuPostRequest.Card.Element();
        element.setContent(content.toString());
        elements.add(element);
        FeishuPostRequest.Card card = new FeishuPostRequest.Card();
        card.setElements(elements);
        FeishuPostRequest.Card.Header header = new FeishuPostRequest.Card.Header();
        FeishuPostRequest.Card.Header.Title title = new FeishuPostRequest.Card.Header.Title();
        title.setContent(titleName);
        header.setTitle(title);
        header.setTemplate(color != null ? color : header.getTemplate());
        card.setHeader(header);
        FeishuPostRequest feishuPostRequest = new FeishuPostRequest();
        feishuPostRequest.setCard(card);

        HttpClient.post(this.globalDefaultProperties.getFeishu().getWebhooks(), (JSONObject) JSON.toJSON(feishuPostRequest));
    }

    public void send(String titleName, Object content, String ex) {
        send(titleName, content, ex, null);
    }

    private String getEnv() {
        String env = System.getProperty("hll.env");
        return env != null ? env : "未知";
    }
}
