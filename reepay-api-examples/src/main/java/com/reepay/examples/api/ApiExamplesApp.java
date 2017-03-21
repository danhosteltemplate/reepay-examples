package com.reepay.examples.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;

@SpringBootApplication
public class ApiExamplesApp implements CommandLineRunner {
    
    @Inject
    private ChargeExamples chargeExamples;
    
    @Inject
    private SubscriptionExamples subscriptionExamples;

    @Override
    public void run(String... args) throws Exception {
        chargeExamples.run();
        subscriptionExamples.run();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApiExamplesApp.class, args);
    }

}
