/*
 *       Copyright© (2020).
 */
package com.tony.page;

import lombok.Data;

/**
 * @author tony
 * @create 2021-10-19
 * @description:
 */
@Data
public class PageResult<T> extends Result<T> {

    private long total;
    private long pageNo;
    private long pageSize;

    public PageResult(long total, long pageNo, long pageSize, T data) {
        this.data = data;
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static <T> PageResult<T> of(PageAjaxResult<T> ajaxResult) {
        return new PageResult<>(ajaxResult.getTotal(), ajaxResult.getPageNo(), ajaxResult.getPageSize(), ajaxResult.getData());
    }

}