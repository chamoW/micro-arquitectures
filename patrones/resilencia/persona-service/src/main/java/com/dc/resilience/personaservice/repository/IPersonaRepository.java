package com.dc.resilience.personaservice.repository;

import com.dc.resilience.personaservice.document.Persona;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IPersonaRepository extends ReactiveMongoRepository<Persona, String> {

}
