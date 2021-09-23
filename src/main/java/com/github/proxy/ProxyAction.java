package com.github.proxy;

public interface ProxyAction {
    boolean doBefore();
    void doAfter();
}