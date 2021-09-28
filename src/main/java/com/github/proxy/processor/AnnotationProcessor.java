package com.github.proxy.processor;

public interface AnnotationProcessor {
    void process(Object object);
    ProxyContext proxyContext();
}
