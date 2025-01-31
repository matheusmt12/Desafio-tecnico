package com.matheus.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheus.desafio.dto.ResponsavelTarefaDTO;
import com.matheus.desafio.entity.ResponsavelTarefa;

@Repository
public interface ResponsavelTarefaRepository extends JpaRepository<ResponsavelTarefa, Integer> {

    // @Query(value = "SELECT * FROM  TB_RESPONSAVEL_TAREFA", nativeQuery = true)
    // List<ResponsavelTarefa> getAll();

    @Query(value = """
            SELECT RT.ID, RT.NOME FROM TB_RESPONSAVEL_TAREFA RT
            """,nativeQuery = true)

    List<ResponsavelTarefaDTO> getAll();

    @Query(value = "SELECT id , nome FROM TB_RESPONSAVEL_TAREFA WHERE id = :id LIMIT 1" ,nativeQuery = true)
    ResponsavelTarefaDTO findById(@Param("id") int id);

}