package com.matheus.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.dto.AlterarStatusDTO;
import com.matheus.desafio.dto.ProjetoDTO;
import com.matheus.desafio.exceptions.DataInvalidaException;
import com.matheus.desafio.exceptions.FinalizarProjetoException;
import com.matheus.desafio.exceptions.NoFindProjetoException;
import com.matheus.desafio.service.ProjetoService;

import jakarta.validation.Valid;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/index")
    public ResponseEntity<?> getAllIndex(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size, @RequestParam(defaultValue = "") String nome,
            @RequestParam(defaultValue = "") String status) {
        try {
            return new ResponseEntity<>(service.getPageProjetos(page, size, nome,status), HttpStatus.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody @Valid ProjetoDTO projeto) {
        try {
            return new ResponseEntity<>(service.insert(projeto), HttpStatus.valueOf(201));
        }catch(DataInvalidaException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.valueOf(400));
        } 
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PutMapping("/alterarStatusProjeto/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable int id, @RequestBody AlterarStatusDTO alterar) {
        try {
            return new ResponseEntity<>(service.alterarStatus(id, alterar), HttpStatus.valueOf(200));
        } catch (FinalizarProjetoException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(400));
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        try {
            return new ResponseEntity<>(service.getId(id), HttpStatus.valueOf(200));
        } catch (NoFindProjetoException noProject) {
            return new ResponseEntity<>(noProject.getMessage(), HttpStatus.valueOf(404));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String ,String> handleValidateException (MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nomeCampo = ((FieldError) error).getField();
            String erroMenssagem = error.getDefaultMessage();
            errors.put(nomeCampo, erroMenssagem);
        });

        return errors;
    }

}
