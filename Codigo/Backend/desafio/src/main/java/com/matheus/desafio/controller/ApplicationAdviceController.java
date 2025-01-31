package com.matheus.desafio.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.matheus.desafio.exceptions.DataInvalidaException;
import com.matheus.desafio.exceptions.FinalizarProjetoException;
import com.matheus.desafio.exceptions.NoFindProjetoException;
import com.matheus.desafio.exceptions.NoFindResponsavelProjetoException;
import com.matheus.desafio.exceptions.NoFindResponsavelTarefaException;
import com.matheus.desafio.exceptions.SenhaNoFoundException;
import com.matheus.desafio.exceptions.UsuarioNoFoundException;


@RestControllerAdvice
public class ApplicationAdviceController {

    @ExceptionHandler(DataInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDataInvalidaException(DataInvalidaException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(FinalizarProjetoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFinalizarProjetoException(FinalizarProjetoException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NoFindProjetoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlNoFindProjetoException(NoFindProjetoException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(SenhaNoFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlSenhaNoFoundException(SenhaNoFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UsuarioNoFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUsuarioNoFoundException(UsuarioNoFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NoFindResponsavelProjetoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoFindResponsavelProjetoException(NoFindResponsavelProjetoException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(NoFindResponsavelTarefaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoFindResponsavelTarefaException(NoFindResponsavelTarefaException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidateException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nomeCampo = ((FieldError) error).getField();
            String erroMenssagem = error.getDefaultMessage();
            errors.put(nomeCampo, erroMenssagem);
        });

        return errors;
    }
}
