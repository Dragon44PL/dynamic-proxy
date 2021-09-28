package com.github.proxy.processor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProxyContext {

    private final Map<Class<?>, Object> data;

    public ProxyContext() {
        this.data = new HashMap<>();
    }

    public void registerProxy(Class<?> clazz, Object proxy) {
        final Class<?>[] interfaces = clazz.getInterfaces();
        Arrays.stream(interfaces).forEach(inter -> {
            if(!data.containsKey(inter)) {
                data.put(inter, proxy);
            }
        });
    }

    public Optional<Object> findProxy(Class<?> clazz) {
        return Optional.ofNullable(data.get(clazz));
    }

}
