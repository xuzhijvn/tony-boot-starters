package com.tony.boot.notify;

import java.lang.reflect.Method;

public interface ExceptionNotifier {

    void notify(Method method, Object[] args, Throwable ex);
}
