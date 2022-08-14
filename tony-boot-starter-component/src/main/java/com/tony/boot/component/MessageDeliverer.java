/*
 *       Copyright© (2020).
 */
package com.tony.boot.component;

/**
 * @author tony
 * @create 2021-07-31
 * @description:
 */
public interface MessageDeliverer {

    /**
     * 发送消息
     *
     * @param request
     * @param <T>
     */
    <T> void send(T request);
}
