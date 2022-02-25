package com.dc.resilience.personaservice.service;

import com.dc.resilience.personaservice.document.Persona;
import com.dc.resilience.personaservice.repository.IPersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final IPersonaRepository personaRepository;

    public Mono<Persona> getPersonaById(String id){
        return personaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("No person with Id " + id + "found")));
    }
}
