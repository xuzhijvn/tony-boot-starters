package io.github.xuzhijvn.tonyboot.notify;

public interface Notifier<T> {


    void notify(String message);


    void notify(T t);

}
