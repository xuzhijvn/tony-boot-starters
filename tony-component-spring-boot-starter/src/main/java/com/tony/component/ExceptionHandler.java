package com.tony.component;

import java.lang.reflect.Method;

public interface ExceptionHandler {

    void handle(Method method, Object[] args, Throwable ex);

}
