package org.example.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    InitService initService;

    @Override
    public void run(String... args) throws Exception {
        initService.initAdmin();
    }
}
