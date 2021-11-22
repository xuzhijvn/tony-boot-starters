/*
 *       Copyright© (2020).
 */
package com.tony.dp.converter;

import com.tony.dp.model.Permission;
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
