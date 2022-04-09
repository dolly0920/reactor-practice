package com.dolly0920.reactorpratice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ReactorPraticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactorPraticeApplication.class, args);
    }

}
