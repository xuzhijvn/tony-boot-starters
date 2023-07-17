/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.dp.converter;

import io.github.xuzhijvn.tonyboot.dp.model.Permission;
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
