package com.dc.resilience.saludoservice.controller;

import com.dc.resilience.saludoservice.document.Saludo;
import com.dc.resilience.saludoservice.service.SaludoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saludo")
public class SaludoController {

    private final SaludoService saludoService;


    @GetMapping("/{id}")
    public Saludo getSaludo(@PathVariable String id) {
        return saludoService.obtenerSaludo(id);
    }

}
