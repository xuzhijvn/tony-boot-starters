/*
 *       Copyright© (2020).
 */
package com.tony.boot.dp.converter;

import com.tony.boot.dp.model.Permission;
import org.springframework.stereotype.Component;

/**
 * @author tony
 * @create 2021-09-02
 * @description:
 */
@Component
public interface IDataConverter<T> {

    /**
     * convert
     *
     * @param t
     * @return
     */
    Permission convert(T t);
}
