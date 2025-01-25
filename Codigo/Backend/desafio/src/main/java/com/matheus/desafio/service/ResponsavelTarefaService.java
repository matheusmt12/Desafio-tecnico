package com.matheus.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.desafio.dto.ResponsavelTarefaDTO;
import com.matheus.desafio.repository.ResponsavelTarefaRepository;

@Service
public class ResponsavelTarefaService {

    @Autowired
    private ResponsavelTarefaRepository repository;

    public List<ResponsavelTarefaDTO> getAll() {

        return repository.getAll();
    }

}
