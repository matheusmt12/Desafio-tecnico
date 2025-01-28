package com.matheus.desafio.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.dto.LoginDTO;
import com.matheus.desafio.dto.TokenDTO;
import com.matheus.desafio.entity.Usuario;
import com.matheus.desafio.exceptions.SenhaNoFoundException;
import com.matheus.desafio.exceptions.UsuarioNoFoundException;
import com.matheus.desafio.security.CustomUserDetailService;
import com.matheus.desafio.security.JwtService;
import com.matheus.desafio.service.UsuarioService;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    private UsuarioService service;
    private CustomUserDetailService customService;
    private JwtService jwtService;

    public UsuarioController(UsuarioService service, CustomUserDetailService customService, JwtService jwtService) {
        this.customService = customService;
        this.jwtService = jwtService;
        this.service = service;
    }

    @Value("${security.jwt.expiration}")
    private String tempoToken;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(service.insertUsuario(usuario), HttpStatus.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(service.findByUsuario("matheus"), HttpStatus.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        customService.verifyUserCredentials(login);
        String token = jwtService.generateToken(login.getUsuario());
        return new ResponseEntity<>(new TokenDTO(token, tempoToken), HttpStatus.valueOf(200));

    }

}
