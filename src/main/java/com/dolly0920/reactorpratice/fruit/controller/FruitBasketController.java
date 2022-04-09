package com.dolly0920.reactorpratice.fruit.controller;

import com.dolly0920.reactorpratice.fruit.service.FruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FruitBasketController {

  private final FruitService fruitService;

  @GetMapping("/mono/test")
  public Mono<String> monoTest() {
    return fruitService.monoTest();
  }

  @GetMapping("/flux/test")
  public Flux<String> fluxTest() {
    return fruitService.fluxTest();
  }
}
