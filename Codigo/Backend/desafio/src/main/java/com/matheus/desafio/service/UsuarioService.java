package com.matheus.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.desafio.entity.Usuario;
import com.matheus.desafio.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public Usuario findByUsuario (String usuario){
        return repository.findByUsuario(usuario);
    }
}
