package com.matheus.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.dto.AlterarStatusDTO;
import com.matheus.desafio.dto.TarefaDTO;
import com.matheus.desafio.service.TarefaService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/tarefa")
@RestController
public class TarefaContoller {

    @Autowired
    private TarefaService service;

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/index/{idProjeto}")
    public ResponseEntity<?> getAllIndex(@PathVariable int idProjeto, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size, @RequestParam(defaultValue = "") String titulo,
            @RequestParam(defaultValue = "") String status) {
        try {
            return new ResponseEntity<>(service.getPageTarefa(idProjeto, page, size, titulo, status),
                    HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid TarefaDTO tarefa) {
        try {
            return new ResponseEntity<>(service.insert(tarefa), HttpStatus.valueOf(201));
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getIdProjeto(@PathVariable int id) {
        try {
            return new ResponseEntity<>(service.getTarefaIdPRojeto(id), HttpStatus.valueOf(200));
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarStatus(@PathVariable int id, @RequestBody AlterarStatusDTO alterar) {
        try {
            return new ResponseEntity<>(service.alterarStatus(id, alterar), HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
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
