package com.tony.component.constant;

import java.util.stream.Stream;

/**
 * @author tony
 * @create 2021/11/17
 * @description:
 */
public enum Color {

    RED(1, "red"),
    BLUE(2, "blue"),
    GREEN(3, "green"),
    YELLOW(4, "yellow");

    private Integer key;
    private String value;

    Color(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Color toType(int key) {
        return Stream.of(Color.values())
                .filter(p -> p.key == key)
                .findAny()
                .orElse(null);
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}