package com.matheus.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.entity.Usuario;
import com.matheus.desafio.service.UsuarioService;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    

    @Autowired
    private UsuarioService service;

    @PostMapping("/create")
    public ResponseEntity<?> create ( @RequestBody Usuario usuario ){
        try {
            return new ResponseEntity<>(service.insertUsuario(usuario), HttpStatus.valueOf(201));
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));

        }
    }
}
