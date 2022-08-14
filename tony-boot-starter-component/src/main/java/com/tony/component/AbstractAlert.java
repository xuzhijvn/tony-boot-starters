package com.tony.component;

import java.lang.reflect.Method;

public abstract class AbstractAlert implements Alert{


    protected GlobalDefaultProperties globalDefaultProperties;

    @Override
    public GlobalDefaultProperties getGlobalDefaultProperties() {
        return globalDefaultProperties;
    }

    @Override
    public void sendAsync(Object msg) {

    }

    @Override
    public void sendIfAbsent(Object msg) {

    }

    @Override
    public void sendAsync(String title, Object msg) {

    }

    @Override
    public void sendIfAbsent(String title, Object msg) {

    }

    @Override
    public void sendAsync(Object msg, Throwable ex) {

    }

    @Override
    public void sendIfAbsent(Object msg, Throwable ex) {

    }

    @Override
    public void sendAsync(String title, Object msg, Throwable ex) {

    }

    @Override
    public void sendIfAbsent(String title, Object msg, Throwable ex) {

    }

    @Override
    public void sendAsync(String title, Method method, Object[] args, Throwable ex) {

    }

    @Override
    public void sendIfAbsent(String title, Method method, Object[] args, Throwable ex) {

    }

    @Override
    public void sendAsync(String title, String msg, Method method, Object[] args, Throwable ex) {

    }

    @Override
    public void sendIfAbsent(String title, String msg, Method method, Object[] args, Throwable ex) {

    }
}
