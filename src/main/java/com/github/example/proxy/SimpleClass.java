package com.github.example.proxy;

import com.github.proxy.Proxy;

public class SimpleClass implements Interface {

    @Proxy
    @Override
    public void say() {
        System.out.println("Hello");
    }

    @Override
    public void yes() {
        System.out.println("Yes");
    }
}
