package com.dc.resilience.personaservice.controller;

import com.dc.resilience.personaservice.document.Persona;
import com.dc.resilience.personaservice.service.PersonaService;
import com.dc.resilience.personaservice.webclient.SaludoServiceClient;
import com.dc.resilience.personaservice.webclient.model.Saludo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/persona")
public class PersonaController {

    private final PersonaService personaService;
    private final SaludoServiceClient saludoServiceClient;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<String>> getPersona(@PathVariable String id) {

        Mono<Persona> personaMono = personaService.getPersonaById(id);
        Mono<Saludo> saludoMono = saludoServiceClient.callSadudoService();

        return Mono.zip(personaMono, saludoMono)
                .flatMap(tupla -> {
                    String data = tupla.getT2().getSaludo().concat(" ").concat(tupla.getT1().getNombre());
                    return Mono.just(data);
                })
                .map(saludoString -> ResponseEntity.status(HttpStatus.OK).body(saludoString))
                ;
        //.map(persona -> ResponseEntity.status(HttpStatus.OK).body(persona));

    }


}
