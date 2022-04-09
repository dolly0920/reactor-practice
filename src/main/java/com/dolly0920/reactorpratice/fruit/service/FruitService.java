package com.dolly0920.reactorpratice.fruit.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FruitService {

    public Mono<String> test() {
        return Mono.just("hello world!");
    }
}
