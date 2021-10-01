package com.github.transactional;

import com.github.proxy.DynamicProxyInvocationHandler;

import java.lang.reflect.Method;

public class TransactionalInvocationHandler extends DynamicProxyInvocationHandler<TransactionalAction> {

    public TransactionalInvocationHandler(TransactionalAction proxyAction, Object proxiedInstance) {
        super(proxyAction, proxiedInstance);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invoke(method, args, Transactional.class);
    }

    @Override
    protected Object invokeProxy(Method method, Object[] args) throws Throwable {
        try {
            Object result = null;
            if (proxyAction.doBefore()) {
                result = method.invoke(proxiedInstance, args);
                proxyAction.doAfter();
            }
            return result;
        } catch (Exception e) {
            proxyAction.thrownException(e);
            return null;
        }
    }
}
