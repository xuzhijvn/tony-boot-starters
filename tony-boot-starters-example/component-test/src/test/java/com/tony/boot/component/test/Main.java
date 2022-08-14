/*
 *       Copyright© (2020).
 */
package com.tony.boot.component.test;

import cn.hutool.core.util.NumberUtil;

/**
 * @author tony
 * @create 2021-12-01
 * @description:
 */
public class Main {

    public static void main(String[] args) {

    }

    public static long format(String str) {
        if (NumberUtil.isNumber(str)) {
            return Long.parseLong(str);
        }
        if (str.charAt(str.length() - 1) != 'K' ||
                str.charAt(str.length() - 1) != 'M' ||
                str.charAt(str.length() - 1) != 'G'
        ) {
            return -1;
        }
        String numStr = str.substring(0, str.length() - 1);
        if (!NumberUtil.isNumber(numStr)) {
            return -1;
        }
        long num = Integer.parseInt(numStr);
        if (str.charAt(str.length() - 1) == 'G' && num > 4) {
            return -1;
        }

        if (str.charAt(str.length() - 1)  == 'K'){
            return num * 1024;
        }
        if (str.charAt(str.length() - 1)  == 'M'){
            return num * 1024 * 1024;
        }
        if (str.charAt(str.length() - 1)  == 'G'){
            return num * 1024 * 1024 * 1024;
        }
        return -1;
    }

}
