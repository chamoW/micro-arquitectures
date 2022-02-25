package com.dc.resilience.personaservice;

import com.dc.resilience.personaservice.document.Persona;
import com.dc.resilience.personaservice.repository.IPersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class PersonaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonaServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUp(IPersonaRepository repository) {
        return args -> {
            Flux.fromStream(Stream.of(
                    Persona.builder().id("1").nombre("Paula").build(),
                    Persona.builder().id("2").nombre("Paulina").build(),
                    Persona.builder().id("3").nombre("Paulette").build()))

                    .flatMap(persona -> repository.save(persona))
                    .thenMany(repository.findAll())
                    .collectList()
                    .subscribe(list -> list.forEach(p -> System.out.println(p)))
            ;

        };
    }

    @Bean
    public WebClient webClient() {

        return WebClient.builder().baseUrl("http://localhost:9092").build();

    }

    @Bean
    public Random random() {
        return new Random();
    }
}
