package com.matheus.desafio.exceptions;

public class UsuarioNoFoundException extends RuntimeException{

    public UsuarioNoFoundException(String message){
        super(message);
    }
}
