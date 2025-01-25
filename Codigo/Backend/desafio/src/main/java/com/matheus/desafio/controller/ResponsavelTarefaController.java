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
        try {
            return new ResponseEntity<>(service.getAll(),HttpStatus.valueOf(200));

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }
    


}
