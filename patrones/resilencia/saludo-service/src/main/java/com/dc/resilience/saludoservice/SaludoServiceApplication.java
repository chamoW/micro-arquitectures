package com.dc.resilience.saludoservice;

import com.dc.resilience.saludoservice.document.Saludo;
import com.dc.resilience.saludoservice.repository.ISaludoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SaludoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaludoServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup (ISaludoRepository saludoRepository){
        return (args) -> {
            List<Saludo> saludos = Arrays.asList(
                    Saludo.builder().id("1").saludo("Buenos días").build(),
                    Saludo.builder().id("2").saludo("Buenas tardes").build(),
                    Saludo.builder().id("3").saludo("Buenas noches").build()
            );

            saludoRepository.saveAll(saludos);
            saludoRepository.findAll().forEach(item -> System.out.println(item));

        };
    }

}
