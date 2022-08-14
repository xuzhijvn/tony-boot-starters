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
public class Result<T> {

    protected T data;

    public static <T> Result<T> of(T data) {
        return new Result<>(data);
    }

}