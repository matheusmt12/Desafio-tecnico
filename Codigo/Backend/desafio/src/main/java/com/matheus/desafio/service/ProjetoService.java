package com.matheus.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.desafio.entity.Projeto;
import com.matheus.desafio.repository.ProjetoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    @Transactional
    public int insert(Projeto projeto) {

        repository.insertProjeto(projeto.getNome(),projeto.getDescricao() ,projeto.getData_inicio(), projeto.getData_termino(),
                projeto.getStatus(), projeto.getId_responsavel());

        return repository.getLastInsertId();

    }

    public List<Projeto> getProjetos() {
        return repository.getProjetos();
    }

}
