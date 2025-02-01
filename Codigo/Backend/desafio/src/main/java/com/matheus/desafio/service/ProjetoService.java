package com.matheus.desafio.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matheus.desafio.dto.AlterarStatusDTO;
import com.matheus.desafio.dto.ProjetoDTO;
import com.matheus.desafio.dto.ProjetoPageDTO;
import com.matheus.desafio.dto.ProjetoTarefaStatusDTO;
import com.matheus.desafio.exceptions.DataInvalidaException;
import com.matheus.desafio.exceptions.FinalizarProjetoException;
import com.matheus.desafio.exceptions.NoFindProjetoException;
import com.matheus.desafio.exceptions.NoFindResponsavelProjetoException;
import com.matheus.desafio.repository.ProjetoRepository;
import com.matheus.desafio.repository.ResponsavelProjetoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private ResponsavelProjetoRepository repositoryResponsavel;

    @Transactional
    public String insert(ProjetoDTO projeto) {

        try {

            if (repositoryResponsavel.findById(projeto.getId_responsavel()) == null) {
                throw new NoFindResponsavelProjetoException("Este responsável não existe!");
            }

            if (projeto.getData_termino().isBefore(projeto.getData_inicio())) {
                throw new DataInvalidaException("A data de termino não pode ser menor que a data de inicio!");

            }
            repository.insertProjeto(projeto.getNome(), projeto.getDescricao(), projeto.getData_inicio(),
                    projeto.getData_termino(),
                    projeto.getStatus(), projeto.getId_responsavel());

            return "Novo Projeto Adicionado";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<ProjetoDTO> getProjetos() {
        return repository.getProjetos();
    }

    public Page<ProjetoPageDTO> getPageProjetos(int page, int size, String nome, String status) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            if (nome.isEmpty() && status.isEmpty()) {
                return repository.getProjetosPage(pageable);
            } else if (!nome.isEmpty() && !status.isEmpty()) {
                return repository.getProjetosPageNomeStatus(pageable, status, "%" + nome + "%");
            } else if (!nome.isEmpty() && status.isEmpty()) {
                return repository.getProjetosPageNome(pageable, "%" + nome + "%");
            }
            return repository.getProjetosPageStatus(pageable, status);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String alterarStatus(int id, AlterarStatusDTO alterar) {

        Optional<Integer> projetoId = repository.getByID(id);
        if (projetoId.isEmpty()) {
            throw new NoFindProjetoException("O projeto não foi encontrado");
        }

        if (alterar.getStatus().equals("FINALIZADO")) {
            Optional<ProjetoTarefaStatusDTO> tarefas = repository.vericarStatus(id);
            if (tarefas.isPresent()) {
                throw new FinalizarProjetoException(
                        "A tarefa " + tarefas.get().getNome_tarefa() + " ainda não foi finaliada");
            }
        }

        repository.alterarStatus(id, alterar.getStatus());

        return "Status Alterado";
    }

    public int getId(int id) {
        Optional<Integer> projetoId = repository.getByID(id);
        if (projetoId.isEmpty()) {
            throw new NoFindProjetoException("O projeto não foi encontrado");
        }
        return projetoId.get();
    }
}
