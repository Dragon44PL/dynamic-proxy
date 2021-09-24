package com.github.proxy.factory;

import com.github.proxy.DynamicProxyInvocationHandler;
import com.github.proxy.ProxyAction;

import java.lang.reflect.Proxy;

public class DynamicProxyCreationFactory implements ProxyCreationFactory {

    private final ProxyAction proxyAction;

    public DynamicProxyCreationFactory(ProxyAction proxyAction) {
        this.proxyAction = proxyAction;
    }

    public Object createProxy(Object source) {
        return Proxy.newProxyInstance(
                source.getClass().getClassLoader(),
                source.getClass().getInterfaces(),
                new DynamicProxyInvocationHandler<>(proxyAction, source)
        );
    }
}
