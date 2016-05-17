package com.bdi.duclv.brmh.service;

import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //make test data
        Timer timer = new Timer();
        long period = 60 * 1000; //1 minute
        //timer.schedule(new MakeTestData(), 0, period);
    }
}
