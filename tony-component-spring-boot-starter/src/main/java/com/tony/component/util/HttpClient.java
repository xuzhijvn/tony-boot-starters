package com.tony.component.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author tony
 * @create 2021/7/28
 * @description:
 */

public class HttpClient {

    private final static Logger log = LoggerFactory.getLogger(HttpClient.class);

    private final static int timeoutConnect = 1000;

    private final static int timeoutRead = 10000;

    public static JSONObject fetch(String url, String method, Map<String, String> params) throws Exception {

        if (url.isEmpty()) {
            throw new Exception("url不能为空");
        }

        // 参数
        StringBuffer paramsStr = new StringBuffer();
        if (method.equals("GET")) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
                paramsStr.append(entry.getKey() + "={" + entry.getKey() + "}");
                paramsStr.append("&");
            }
        }
        //paramsStr.append("from_svc=tony-starter");

        // url
        if (url.indexOf("?") > 0) {
            url = url + "&" + paramsStr;
        } else {
            url = url + "?" + paramsStr;
        }

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(timeoutConnect);
        requestFactory.setReadTimeout(timeoutRead);

        RestTemplate restTemplate = new RestTemplate();
        String response = null;

        long start = System.currentTimeMillis();
        try {
            if (method.equals("GET")) {
                response = restTemplate.getForObject(url, String.class, params);
            }
            if (method.equals("POST")) {
                response = restTemplate.postForObject(url, params, String.class);
            }
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("\nurl:{},\nparams:{},\nresponse:{},\nduration:{}", url, params, response, duration);
        }

        return JSON.parseObject(response);
    }

    public static JSONObject get(String url, Map<String, String> params) throws Exception {
        return fetch(url, "GET", params);
    }

    public static JSONObject post(String url, Map<String, String> params) throws Exception {
        return fetch(url, "POST", params);
    }

    public static JSONObject post(String url, JSONObject request) throws RestClientException {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(timeoutConnect);
        requestFactory.setReadTimeout(timeoutRead);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(request.toString(), headers);
        long start = System.currentTimeMillis();
        String responseStr = restTemplate.postForObject(url, formEntity, String.class);
        long duration = System.currentTimeMillis() - start;
        JSONObject response = JSON.parseObject(responseStr);
        log.info("\nurl:{},\nparams:{},\nresponse:{},\nduration:{}", url, request, response, duration);
        return response;
    }
}
