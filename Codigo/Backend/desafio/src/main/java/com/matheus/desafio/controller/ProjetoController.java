package com.matheus.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.entity.Projeto;
import com.matheus.desafio.service.ProjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService service; 

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getProjetos(), HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Projeto projeto) {
        try {
            return new ResponseEntity<>(service.insert(projeto), HttpStatus.valueOf(201));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
    
    
}
