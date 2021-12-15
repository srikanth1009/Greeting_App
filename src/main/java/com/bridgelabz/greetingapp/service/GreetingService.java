package com.bridgelabz.greetingapp.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.greetingapp.model.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService implements IGreetingService {
    private static final String template = "Hello world";
    private final AtomicLong counter = new AtomicLong();


    @Override
    public Greeting greetingMessage() {
        return new Greeting(counter.incrementAndGet(), String.format(template));
    }
}