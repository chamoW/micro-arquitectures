package com.dc.resilience.personaservice.webclient;

import com.dc.resilience.personaservice.webclient.model.Saludo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaludoServiceClient {

    private final WebClient webClient;
    private final Random  ramdom;

    @CircuitBreaker(name = "saludoServiceCircuitBreaker", fallbackMethod = "callSadudoServiceFallback")
    public Mono<Saludo> callSadudoService() {
        return webClient.get().uri("/api/saludo/{id}", randomId())
                .retrieve()
                .bodyToMono(Saludo.class);
    }

    private String randomId() {

        return String.valueOf((ramdom.nextInt(10) % 5) + 1);
    }

    public Mono<Saludo> callSadudoServiceFallback(Exception ex) {
        log.info("Error al llamar al servicio Saludo SERVICE :(");

        return Mono.just(Saludo.builder().saludo(" ... HOLA  :(").build());



    }

}
