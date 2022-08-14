package com.tony.boot.notify;

public interface Notifier<T> {


    void notify(String message);


    void notify(T t);

}
