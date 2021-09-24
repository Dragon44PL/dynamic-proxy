package com.github.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class DynamicProxyInvocationHandler<T extends ProxyAction> implements InvocationHandler {

    protected final T proxyAction;
    protected final Object proxiedInstance;

    public DynamicProxyInvocationHandler(T proxyAction, Object proxiedInstance) {
        this.proxyAction = proxyAction;
        this.proxiedInstance = proxiedInstance;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invoke(method, args, Proxy.class);
    }

    protected Object invoke(Method method, Object[] args, Class<? extends Annotation> annotation) throws Throwable {
        Object result = null;

        try {
            result = hasDeclaredClassProxy(annotation) || foundProxiedMethod(proxiedInstance, method, annotation)
                    ? invokeProxy(method, args)
                    : invokeDefault(method, args);
        } catch(InvocationTargetException e) {
            throw e.getTargetException();
        }

        return result;
    }

    protected Object invokeProxy(Method method, Object[] args) throws Throwable {
        Object result = null;
        if (proxyAction.doBefore()) {
            result = method.invoke(proxiedInstance, args);
        }
        proxyAction.doAfter();
        return result;
    }

    protected Object invokeDefault(Method method, Object[] args) throws Throwable {
        return method.invoke(proxiedInstance, args);
    }

    private boolean hasDeclaredClassProxy(Class<? extends Annotation> annotation) {
        return proxiedInstance.getClass().getAnnotation(annotation) != null;
    }

    private boolean foundProxiedMethod(Object proxiedInstance, Method method, Class<? extends Annotation> annotation) {
        Optional<Method> found = Arrays.stream(proxiedInstance.getClass().getMethods()).filter(next -> next.getName().equals(method.getName())).findAny();
        return found.isPresent() && found.get().isAnnotationPresent(annotation);
    }
}
