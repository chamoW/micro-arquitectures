package com.dc.resilience.personaservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class PersonaControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<String>> exceptinHandler(RuntimeException ex){
        log.info("ups ocurrio un error :( -> {}", ex.getMessage());

        return Mono.defer(() -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage())));
    }
}
