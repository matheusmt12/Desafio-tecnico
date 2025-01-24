package com.matheus.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.desafio.dto.TarefaDTO;
import com.matheus.desafio.entity.Tarefa;
import com.matheus.desafio.repository.TarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> getAll() {

        return repository.getAll();
    }

    @Transactional
    public String insert(TarefaDTO tarefa) {
        try {
            repository.insertTarega(tarefa.getTitulo(), tarefa.getDescricao(),
                    tarefa.getPrazo(), tarefa.getStatus(), tarefa.getId_projeto(),
                    tarefa.getId_responsavel());

            return "Sucesso, Tarefa Criada";
        } catch (Exception e) {
            // TODO: handle exception
            return e.getMessage();
        }
    }
}
