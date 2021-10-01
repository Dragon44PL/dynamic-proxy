package com.github.transactional;

import com.github.proxy.ProxyAction;

public interface TransactionalAction extends ProxyAction {
    <T extends Exception> void thrownException(T e);
}