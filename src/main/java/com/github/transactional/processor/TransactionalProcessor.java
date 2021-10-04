package com.github.transactional.processor;

import com.github.proxy.processor.ProxyAnnotationProcessor;
import com.github.transactional.Transactional;
import com.github.transactional.TransactionalAction;
import com.github.transactional.factory.TransactionalCreationFactory;

public class TransactionalProcessor extends ProxyAnnotationProcessor {

    public TransactionalProcessor(TransactionalAction transactionalAction) {
        super(new TransactionalCreationFactory(transactionalAction), Transactional.class);
    }

}
