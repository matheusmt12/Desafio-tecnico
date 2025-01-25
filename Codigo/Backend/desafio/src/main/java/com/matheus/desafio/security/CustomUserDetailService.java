package com.matheus.desafio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.matheus.desafio.dto.LoginDTO;
import com.matheus.desafio.entity.Usuario;
import com.matheus.desafio.exceptions.SenhaNoFoundException;
import com.matheus.desafio.exceptions.UsuarioNoFoundException;
import com.matheus.desafio.service.UsuarioService;

@Service
public class CustomUserDetailService  implements UserDetailsService{
    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario user = service.findByUsuario(usuario);
        if (user == null) {
            throw new UsuarioNoFoundException("Usuario não encontrado");
        }
        return new UserPrincipal(user);
    }

    public void verifyUserCredentials(LoginDTO loginDto) {

        UserDetails user = loadUserByUsername(loginDto.getUsuario());
        boolean verify = SecurityConfig.passwordEncoder().matches(loginDto.getSenha(), user.getPassword());
        if (!verify) {
            throw new SenhaNoFoundException("Senha inválida");
        }

    }

}
