package com.tony.boot.component;

import java.lang.reflect.Method;

public interface ExceptionHandler {

    void handle(Method method, Object[] args, Throwable ex);

}
