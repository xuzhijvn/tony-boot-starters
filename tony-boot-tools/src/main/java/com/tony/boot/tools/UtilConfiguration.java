package com.tony.boot.tools;

import com.tony.boot.tools.utils.spring.SpringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xuzhijun@htsc.com
 */
@Import(SpringUtils.class)
@Configuration
public class UtilConfiguration {
}
