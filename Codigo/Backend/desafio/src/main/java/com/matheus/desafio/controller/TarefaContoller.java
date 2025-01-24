package com.matheus.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.entity.Tarefa;
import com.matheus.desafio.service.TarefaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/tarefa")
@RestController
public class TarefaContoller {

    @Autowired
    private TarefaService service;

    @GetMapping
    public ResponseEntity<?> get(){
        try {
            return new ResponseEntity<>(service.getAll(),HttpStatus.valueOf(200) );
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }
    }

    @PostMapping
    public ResponseEntity<?> post (@RequestBody Tarefa tarefa){
        try {
            return new ResponseEntity<>(service.insert(tarefa), HttpStatus.valueOf(201));
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(500));
        }

    }   

}
