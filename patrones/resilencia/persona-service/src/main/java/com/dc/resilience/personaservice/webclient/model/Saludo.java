package com.dc.resilience.personaservice.webclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data //Genera getters and setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Saludo {

    @Id
    private String id;

    private String saludo;

}
