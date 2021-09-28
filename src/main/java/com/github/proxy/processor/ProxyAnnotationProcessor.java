package com.github.proxy.processor;

import com.github.proxy.Proxy;
import com.github.proxy.factory.ProxyCreationFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class ProxyAnnotationProcessor implements AnnotationProcessor {

    private final ProxyContext proxyContext;
    private final ProxyCreationFactory proxyCreationFactory;
    private final Class<? extends Annotation> annotation;

    private static final Class<? extends Annotation> PROXY_ANNOTATION = Proxy.class;

    public ProxyAnnotationProcessor(ProxyCreationFactory proxyCreationFactory) {
        this(proxyCreationFactory, PROXY_ANNOTATION);
    }

    protected ProxyAnnotationProcessor(ProxyCreationFactory proxyCreationFactory, Class<? extends Annotation> clazz) throws NotProxyAnnotationException {

        if(!isAnnotatedClass(clazz)) {
            throw new NotProxyAnnotationException();
        }

        this.proxyCreationFactory = proxyCreationFactory;
        this.proxyContext = new ProxyContext();
        this.annotation = clazz;
    }

    public void process(Object object) {
        Objects.requireNonNull(object);
        if((hasProxiedClass(object.getClass()) || hasProxiedMethods(object.getClass()) && hasDeclaredInterfaces(object.getClass()))) {
            final Object proxy = proxyCreationFactory.createProxy(object);
            proxyContext.registerProxy(object.getClass(), proxy);
        }
    }

    private boolean isAnnotatedClass(Class<? extends Annotation> annotation) {
        return annotation.equals(PROXY_ANNOTATION) || annotation.getAnnotation(PROXY_ANNOTATION) != null;
    }

    private boolean hasProxiedClass(Class<?> clazz) {
        return clazz.getAnnotation(annotation) != null;
    }

    private boolean hasDeclaredInterfaces(Class<?> clazz) {
        return clazz.getInterfaces().length > 0;
    }

    private boolean hasProxiedMethods(Class<?> clazz) {
        final Method[] methods = clazz.getMethods();
        return Arrays.stream(methods).anyMatch(method -> method.isAnnotationPresent(annotation));
    }

    public ProxyContext proxyContext() {
        return proxyContext;
    }
}