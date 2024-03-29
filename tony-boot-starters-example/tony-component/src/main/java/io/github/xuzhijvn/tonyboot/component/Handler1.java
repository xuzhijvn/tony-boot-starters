package io.github.xuzhijvn.tonyboot.component;

import com.tony.boot.component.ExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Handler1 implements ExceptionHandler {
    @Override
    public void handle(Method method, Object[] args, Throwable ex) {
        System.out.println("Handler1: " + method.getName() + " " + Arrays.toString(args) + " " + ex.getMessage());
    }
}
