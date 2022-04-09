package com.dolly0920.reactorpratice.fruit.service;

import java.util.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FruitService {

  final List<String> basket1 = Arrays.asList("kiwi", "orange", "lemon", "kiwi");
  final List<String> basket2 = Arrays.asList("banana", "lemon", "lemon", "kiwi");
  final List<String> basket3 =
      Arrays.asList("strawberry", "orange", "lemon", "grape", "strawberry");

  final List<List<String>> baskets = Arrays.asList(basket1, basket2, basket3);

  public Mono<String> monoTest() {

    return Mono.just("hello world!");
  }

  public Flux<String> fluxTest() {
    return Flux.fromIterable(baskets)
        .concatMap(
            basket -> {
              final Mono<List<String>> distinctFruits =
                  Flux.fromIterable(basket).distinct().collectList();
              final Mono<Map<String, Long>> countFruitsMono =
                  Flux.fromIterable(basket)
                      .groupBy(fruit -> fruit)
                      .flatMapSequential(
                          groupedFlux ->
                              groupedFlux
                                  .count()
                                  .map(
                                      count -> {
                                        final Map<String, Long> fruitCount = new LinkedHashMap<>();
                                        fruitCount.put(groupedFlux.key(), count);
                                        return fruitCount;
                                      }))
                      .reduce(
                          (accumulateMap, currentMap) ->
                              new LinkedHashMap<String, Long>() {
                                {
                                  putAll(accumulateMap);
                                  putAll(currentMap);
                                }
                              });
              return Flux.zip(
                  distinctFruits,
                  countFruitsMono,
                  (distinct, count) -> String.format("distinct : %s, count : %s", distinct, count) + "/");
            });
  }
}
