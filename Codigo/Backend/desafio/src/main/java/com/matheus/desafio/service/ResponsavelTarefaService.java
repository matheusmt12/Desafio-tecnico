package com.matheus.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.desafio.entity.ResponsavelTarefa;
import com.matheus.desafio.repository.ResponsavelTarefaRepository;

@Service
public class ResponsavelTarefaService {

    @Autowired
    private ResponsavelTarefaRepository repository;

    public List<ResponsavelTarefa> getAll() {

        return repository.getAll();
    }

}
