package com.itheima.hystriThread;

import java.util.concurrent.Callable;

public interface HystrixCallableWrapper {

    <T> Callable<T> wrap(Callable<T> callable);
}
