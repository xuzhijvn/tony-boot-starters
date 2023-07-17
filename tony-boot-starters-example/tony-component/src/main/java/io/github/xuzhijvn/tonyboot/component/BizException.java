/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.component;

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
