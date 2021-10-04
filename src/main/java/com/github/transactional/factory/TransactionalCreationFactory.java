package com.github.transactional.factory;

import com.github.proxy.factory.ProxyCreationFactory;
import com.github.transactional.TransactionalAction;
import com.github.transactional.TransactionalInvocationHandler;

import java.lang.reflect.Proxy;

public class TransactionalCreationFactory implements ProxyCreationFactory {

    private final TransactionalAction action;

    public TransactionalCreationFactory(TransactionalAction transactionalAction) {
        this.action = transactionalAction;
    }

    @Override
    public Object createProxy(Object source) {
        return Proxy.newProxyInstance(
                source.getClass().getClassLoader(),
                source.getClass().getInterfaces(),
                new TransactionalInvocationHandler(action, source)
        );
    }
}
