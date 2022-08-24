package com.tony.boot.notify;


public interface ExceptionNotifier {

    void notify(Throwable throwable);

    void notify(Object[] args, Throwable throwable);
}
