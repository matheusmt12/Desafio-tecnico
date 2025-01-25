package com.matheus.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.desafio.dto.ResponseDTO;
import com.matheus.desafio.entity.Usuario;
import com.matheus.desafio.repository.UsuarioRepository;
import com.matheus.desafio.security.SecurityConfig;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public Usuario findByUsuario (String usuario){
        return repository.findByUsuario(usuario);
    }


    @Transactional
    public ResponseDTO<?> insertUsuario(Usuario usuario){

        try {
            
            repository.insertUsuario(usuario.getUsuario(),SecurityConfig.passwordEncoder().encode(usuario.getSenha()));
            return new ResponseDTO<>("Sucesso", null);
        } catch (Exception e) {
            return new ResponseDTO<>(e.getMessage(),null);
        }
    }
}
