package com.matheus.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheus.desafio.entity.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM TB_TAREFAS")
    List<Tarefa> getAll();

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO TB_TAREFAS(titulo,descricao,prazo,status,id_projeto,id_responsavel)"
            +
            "VALUES(:titulo, :descricao, :prazo, :status, :id_projeto, :id_responsavel)")
    void insertTarega(@Param("titulo") String titulo, @Param("descricao") String descricao, @Param("prazo") int prazo,
            @Param("status") String status, @Param("id_projeto") int id_projeto,
            @Param("id_responsavel") int id_responsavel);
}
