package com.dc.resilience.saludoservice.service;

import com.dc.resilience.saludoservice.document.Saludo;
import com.dc.resilience.saludoservice.repository.ISaludoRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor //Crear un constructor con las propiedades finales
public class SaludoService {

    private final ISaludoRepository saludoRepository;


    @CircuitBreaker(name = "saludoRepositoryCircuitBreaker", fallbackMethod = "obtenerSaludoFallback")
    public Saludo obtenerSaludo(String id) {
        return saludoRepository.findById(id).get(); //Va a tronar
    }


    public Saludo obtenerSaludoFallback(String id, Exception ex) {
        log.info("UPS --> Saludo id {} no encontrado, retornando fallback", id);

        return Saludo.builder().id(id).saludo("FALLBACK SALUDO").build();

    }
}
