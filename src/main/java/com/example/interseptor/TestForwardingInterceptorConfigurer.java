package com.example.interseptor;

import com.github.mkopylec.charon.forwarding.interceptors.RequestForwardingInterceptorConfigurer;

public class TestForwardingInterceptorConfigurer extends RequestForwardingInterceptorConfigurer<TestForwardingInterseptor> {

    protected TestForwardingInterceptorConfigurer() {
        super(new TestForwardingInterseptor());
    }

    public static TestForwardingInterceptorConfigurer testForwardingInterceptor() {
        return new TestForwardingInterceptorConfigurer();
    }
}
