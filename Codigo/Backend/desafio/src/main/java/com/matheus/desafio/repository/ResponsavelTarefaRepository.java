package com.matheus.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.matheus.desafio.dto.ResponsavelTarefaDTO;
import com.matheus.desafio.entity.ResponsavelTarefa;

@Repository
public interface ResponsavelTarefaRepository extends JpaRepository<ResponsavelTarefa, Integer> {

    // @Query(value = "SELECT * FROM  TB_RESPONSAVEL_TAREFA", nativeQuery = true)
    // List<ResponsavelTarefa> getAll();

    @Query(value = """
            SELECT RT.ID, RT.NOME, T.titulo as nomeProjeto, T.STATUS FROM TB_RESPONSAVEL_TAREFA RT JOIN TB_TAREFAS T ON (RT.ID = T.ID_RESPONSAVEL)
            """,nativeQuery = true)

    List<ResponsavelTarefaDTO> getAll();

}