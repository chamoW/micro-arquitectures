package com.dc.resilience.saludoservice.repository;

import com.dc.resilience.saludoservice.document.Saludo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ISaludoRepository extends MongoRepository<Saludo, String> {
}
