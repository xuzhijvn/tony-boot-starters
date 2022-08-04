package com.tony.component;

import java.lang.reflect.Method;

public interface Alert {

    GlobalDefaultProperties getGlobalDefaultProperties();

    void sendAsync(Object msg);

    void sendIfAbsent(Object msg);

    void sendAsync(String title, Object msg);

    void sendIfAbsent(String title, Object msg);

    void sendAsync(Object msg, Throwable ex);

    void sendIfAbsent(Object msg, Throwable ex);

    void sendAsync(String title, Object msg, Throwable ex);

    void sendIfAbsent(String title, Object msg, Throwable ex);

    void sendAsync(String title, Method method, Object[] args, Throwable ex);

    void sendIfAbsent(String title, Method method, Object[] args, Throwable ex);

    void sendAsync(String title, String msg, Method method, Object[] args, Throwable ex);

    void sendIfAbsent(String title, String msg, Method method, Object[] args, Throwable ex);
}
