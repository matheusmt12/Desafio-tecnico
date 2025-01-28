package com.matheus.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.service.ResponsavelProjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/responsavelprojeto")
public class ResponsavelProjetoController {

    @Autowired
    ResponsavelProjetoService service;

    @GetMapping()
    public ResponseEntity<?> get() {
            return new ResponseEntity<>(service.getAll(), HttpStatus.valueOf(200));
    }

}
