package com.github.example.proxy;

import com.github.proxy.ProxyAction;

public class DoNothingProxy implements ProxyAction {

    @Override
    public boolean doBefore() {
        System.out.println("Doing nothin' before");
        return true;
    }

    @Override
    public void doAfter() {
        System.out.println("Doing nothin' after\n");
    }
}
