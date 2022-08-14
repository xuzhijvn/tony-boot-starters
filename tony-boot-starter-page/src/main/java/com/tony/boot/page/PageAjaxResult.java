/*
 *       Copyright© (2020).
 */
package com.tony.boot.page;

import lombok.Data;

import java.util.Objects;

/**
 * @author tony
 * @create 2021-10-19
 * @description:
 */
@Data
public class PageAjaxResult<T> extends AjaxResult<T> {

    private long total;
    private long pageNo;
    private long pageSize;

    public PageAjaxResult() {
        this.setCode(CODE_SUCCESS);
        this.setMsg(MSG_SUCCESS);
    }

    public PageAjaxResult(AjaxResult<T> ajaxResult) {
        this();
        if (Objects.nonNull(ajaxResult)) {
            setCode(ajaxResult.getCode());
            setMsg(ajaxResult.getMsg());
        }
    }
}