package com.github.example.transactional;

import com.github.transactional.Transactional;

public class Simple implements Interface {

    @Override
    @Transactional
    public void transaction() {
        System.out.println("Do some stuff...");
        throw new RuntimeException();
    }
}
