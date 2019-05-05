package com.itheima.hystriThread;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

public class RequestAttributeAwareCallableWrapper implements HystrixCallableWrapper {
    public RequestAttributeAwareCallableWrapper() {
        System.out.println("创建一个RequestAttributeAwareCallableWrapper");
    }

    @Override
    public <T> Callable<T> wrap(Callable<T> callable) {
        return new RequestAttributeAwareCallable(callable, RequestContextHolder.getRequestAttributes());
    }

    static class RequestAttributeAwareCallable<T> implements Callable<T> {

        private final Callable<T> delegate;
        private final RequestAttributes requestAttributes;

        RequestAttributeAwareCallable(Callable<T> callable, RequestAttributes requestAttributes) {
            this.delegate = callable;
            this.requestAttributes = requestAttributes;
        }

        @Override
        public T call() throws Exception {
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes);
                return delegate.call();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }

}
