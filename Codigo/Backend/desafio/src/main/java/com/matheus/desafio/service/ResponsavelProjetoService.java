package com.matheus.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.desafio.entity.ResponsavelProjeto;
import com.matheus.desafio.repository.ResponsavelProjetoRepository;

@Service
public class ResponsavelProjetoService {


    @Autowired
    
    private ResponsavelProjetoRepository repository;

    public List<ResponsavelProjeto> getAll(){

        return repository.getAll();
    }
}
