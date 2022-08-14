/*
 *       Copyright© (2020).
 */
package com.tony.boot.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tony
 * @create 2021-10-19
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult<T> extends Result<T> {

    public static final int CODE_SUCCESS = 200;
    public static final int CODE_UNAUTHORIZED = 401;
    public static final int CODE_FORBIDDEN = 403;
    public static final int CODE_ERROR = 500;

    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_FAILED = "操作失败";
    public static final String MSG_NOT_PERMISSION = "用户权限不足";
    public static final String MSG_UNAUTHORIZED = "用户未登录或身份已过期";

    private int code;
    private String msg;
    //private T data;

    public AjaxResult(int code, String msg, T data) {
        super(data);
        this.code = code;
        this.msg = msg;
    }

    public static <T> AjaxResult<T> success(int code, T data) {
        return new AjaxResult<>(code, MSG_SUCCESS, data);
    }

    public static <T> AjaxResult<T> success(T data) {
        return success(CODE_SUCCESS, data);
    }

    public static <T> AjaxResult<T> success() {
        return success(CODE_SUCCESS, null);
    }

    public static <T> AjaxResult<T> error(int code, String msg) {
        return new AjaxResult<>(code, msg, null);
    }

    public static <T> AjaxResult<T> error(String msg) {
        return error(CODE_ERROR, msg);
    }

    public static <T> AjaxResult<T> error() {
        return new AjaxResult<>(CODE_ERROR, MSG_FAILED, null);
    }

}