package com.matheus.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matheus.desafio.dto.AlterarStatusDTO;
import com.matheus.desafio.dto.ResponseDTO;
import com.matheus.desafio.dto.TarefaDTO;
import com.matheus.desafio.exceptions.NoFindResponsavelTarefaException;
import com.matheus.desafio.repository.ResponsavelTarefaRepository;
import com.matheus.desafio.repository.TarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired 

    private ResponsavelTarefaRepository repositoryResponsavel;

    public List<TarefaDTO> getAll() {

        return repository.getAll();
    }

    @Transactional
    public String insert(TarefaDTO tarefa) {
        try {
            if (repositoryResponsavel.findById(tarefa.getId_responsavel()) == null) {
                throw new NoFindResponsavelTarefaException("Esse responsável não existe");
            }
            repository.insertTarega(tarefa.getTitulo(), tarefa.getDescricao(),
                    tarefa.getPrazo(), tarefa.getStatus(), tarefa.getId_projeto(),
                    tarefa.getId_responsavel());

            return "Sucesso, Tarefa Criada";
        } catch (Exception e) {
            throw new  RuntimeException(e.getMessage());
        }
    }

    public List<TarefaDTO> getTarefaIdPRojeto(int id) {

        return repository.getTarefaIdProjeto(id);
    }

    @Transactional
    public ResponseDTO<?> alterarStatus(int id, AlterarStatusDTO alterar) {
        try {
            repository.alterarStatusTarefa(id, alterar.getStatus());
            return new ResponseDTO<>("Status Alterado", null);
        } catch (Exception e) {
            return new ResponseDTO<>(e.getMessage(), null);
        }
    }

    public Page<TarefaDTO> getPageTarefa(int idProjeto , int page, int size, String titulo, String status) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            if (titulo.isEmpty() && status.isEmpty())
                return repository.getPageTarefa(pageable ,idProjeto);
            else if (!titulo.isEmpty() && !status.isEmpty())
                return null;
            else if (!titulo.isEmpty() && status.isEmpty())
                return repository.getPageTarefaTitulo(pageable,idProjeto, "%"+titulo+"%");
            return repository.getPageTarefaStatus(pageable,idProjeto, status);
        } catch (Exception e) {
            return null;
        }
    }
}
