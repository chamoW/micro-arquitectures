package com.dc.resilience.saludoservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data //Genera getters and setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Saludo {

    @Id
    private String id;

    private String saludo;

}
