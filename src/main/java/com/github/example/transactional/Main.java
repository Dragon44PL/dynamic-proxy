package com.github.example.transactional;

import com.github.transactional.processor.TransactionalProcessor;

public class Main {

    public static void main(String[] args) {
        // given
        final TransactionalProcessor processor = new TransactionalProcessor(new TransactionProxy());

        // when
        final Interface simple = new Simple();
        processor.process(simple);

        //then
        Interface proxy = (Interface) processor.proxyContext().findProxy(Interface.class).get();

        try {
            System.out.println("\nNormal object: \n");
            simple.transaction();
        } catch (Exception ignored) {}


        System.out.println("\nProxy object: \n");
        proxy.transaction();
    }
}
