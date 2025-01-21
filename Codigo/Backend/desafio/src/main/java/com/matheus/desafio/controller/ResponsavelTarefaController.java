package com.matheus.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.service.ResponsavelTarefaService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/responsaveltarefa")
public class ResponsavelTarefaController {

    @Autowired
    private ResponsavelTarefaService service;


    @GetMapping("")
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(service.getAll(),HttpStatus.valueOf(200));
    }
    


}
