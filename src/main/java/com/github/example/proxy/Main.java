package com.github.example.proxy;

import com.github.proxy.factory.DynamicProxyCreationFactory;
import com.github.proxy.processor.ProxyAnnotationProcessor;

public class Main {

    public static void main(String[] args) {
        // given
        final DynamicProxyCreationFactory dynamicProxyFactory = new DynamicProxyCreationFactory(new DoNothingProxy());
        final ProxyAnnotationProcessor processor = new ProxyAnnotationProcessor(dynamicProxyFactory);

        // when
        final Interface simple = new SimpleClass();
        processor.process(simple);

        //then
        Interface proxy = (Interface) processor.proxyContext().findProxy(Interface.class).get();

        System.out.println("\nNormal object: \n");
        simple.say();
        simple.yes();
        System.out.println("\nProxy object: \n");
        proxy.say();
        proxy.yes();
    }
}
