package com.matheus.desafio.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matheus.desafio.dto.AlterarStatusDTO;
import com.matheus.desafio.dto.ProjetoDTO;
import com.matheus.desafio.dto.ProjetoTarefaStatusDTO;
import com.matheus.desafio.exceptions.DataInvalidaException;
import com.matheus.desafio.exceptions.FinalizarProjetoException;
import com.matheus.desafio.exceptions.NoFindProjetoException;
import com.matheus.desafio.repository.ProjetoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;



    // funcção para adicionar um dia 
    private Date adicionarUmDia(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    @Transactional
    public String insert(ProjetoDTO projeto) {

        Date dataInicio = adicionarUmDia(projeto.getData_inicio());
        Date dataTermino = adicionarUmDia(projeto.getData_termino());


        if (dataInicio.before(new Date())) {
            throw new DataInvalidaException("A data de início não pode ser anterior a hoje!");
        }
        if (dataTermino.before(dataInicio)) {
            throw new DataInvalidaException("A data de termino não pode ser menor que a data de inicio!");

        }

        repository.insertProjeto(projeto.getNome(), projeto.getDescricao(), dataInicio,
                dataTermino,
                projeto.getStatus(), projeto.getId_responsavel());

        return "Novo Projeto Adicionado";
    }

    public List<ProjetoDTO> getProjetos() {
        return repository.getProjetos();
    }

    public Page<ProjetoDTO> getPageProjetos(int page, int size, String nome, String status) {
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
