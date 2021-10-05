package com.github.example.transactional;

import com.github.transactional.TransactionalAction;

public class TransactionProxy implements TransactionalAction {

    @Override
    public boolean doBefore() {
        System.out.println("Before Starting Transaction...");
        return true;
    }

    @Override
    public void doAfter() {
        System.out.println("Commit After Transaction...");
    }

    @Override
    public void thrownException(Exception e) {
        System.out.println("Exception Thrown. Stopping transaction...");
    }
}
