/*
 *       Copyright© (2020).
 */
package com.tony.boot.component.test;

/**
 * @author tony
 * @create 2021-08-19
 * @description:
 */
public class BizException extends RuntimeException {
    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
    }
}
