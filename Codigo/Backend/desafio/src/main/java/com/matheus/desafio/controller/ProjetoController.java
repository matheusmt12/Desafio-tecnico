package com.matheus.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.desafio.dto.AlterarStatusDTO;
import com.matheus.desafio.dto.ProjetoDTO;
import com.matheus.desafio.exceptions.NoFindProjetoException;
import com.matheus.desafio.service.ProjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody ProjetoDTO projeto) {
        try {
            return new ResponseEntity<>(service.insert(projeto), HttpStatus.valueOf(201));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PutMapping("/alterarStatusProjeto/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable int id, @RequestBody AlterarStatusDTO alterar) {
        try {
            return new ResponseEntity<>(service.alterarStatus(id, alterar), HttpStatus.valueOf(200));
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        try {
            return new ResponseEntity<>(service.getId(id), HttpStatus.valueOf(200));
        }catch(NoFindProjetoException noProject){
            return new ResponseEntity<>(noProject.getMessage(),HttpStatus.valueOf(404));
        } 
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
    
    
    
}
