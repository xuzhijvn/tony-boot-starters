package com.tony.boot.common;

import java.util.stream.Stream;

public enum Env {

    DEV, TEST, SIT, UAT, PRD, PROD, UNKNOWN;

    public static Env toType(String env) {
        return Stream.of(Env.values())
                .filter(e -> e.name().equalsIgnoreCase(env))
                .findAny()
                .orElse(UNKNOWN);
    }

}
