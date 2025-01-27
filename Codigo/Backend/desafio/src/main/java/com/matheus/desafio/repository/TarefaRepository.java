package com.matheus.desafio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheus.desafio.dto.TarefaDTO;
import com.matheus.desafio.entity.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

        @Query(nativeQuery = true, value = "SELECT titulo, descricao, prazo, status, id_projeto, id_responsavel FROM TB_TAREFAS")
        List<TarefaDTO> getAll();

        @Modifying
        @Query(nativeQuery = true, value = "INSERT INTO TB_TAREFAS(titulo,descricao,prazo,status,id_projeto,id_responsavel)"
                        +
                        "VALUES(:titulo, :descricao, :prazo, :status, :id_projeto, :id_responsavel)")
        void insertTarega(@Param("titulo") String titulo, @Param("descricao") String descricao,
                        @Param("prazo") int prazo,
                        @Param("status") String status, @Param("id_projeto") int id_projeto,
                        @Param("id_responsavel") int id_responsavel);

        @Query(value = "SELECT T.id, T.titulo, T.descricao, T.prazo, T.status, T.id_projeto, T.id_responsavel, RT.nome as nome_responsavel FROM "
                        + "TB_TAREFAS T JOIN TB_PROJETOS P ON(T.ID_PROJETO = P.ID)" +
                        "LEFT JOIN TB_RESPONSAVEL_TAREFA RT ON (T.id_responsavel = RT.id)" +
                        " WHERE T.id_projeto = :idProjeto", nativeQuery = true)
        List<TarefaDTO> getTarefaIdProjeto(@Param("idProjeto") int idProjeto);

        @Modifying
        @Query(value = "UPDATE TB_TAREFAS SET STATUS = :status WHERE ID = :id", nativeQuery = true)
        void alterarStatusTarefa(@Param("id") int id, @Param("status") String status);


        // page

        @Query(value = "SELECT T.id, T.titulo, T.descricao, T.prazo, T.status, T.id_projeto, T.id_responsavel, RT.nome as nome_responsavel FROM "
                        + "TB_TAREFAS T JOIN TB_PROJETOS P ON(T.ID_PROJETO = P.ID)" +
                        "LEFT JOIN TB_RESPONSAVEL_TAREFA RT ON (T.id_responsavel = RT.id)" +
                        " WHERE T.id_projeto = :idProjeto", nativeQuery = true)
        Page<TarefaDTO> getPageTarefa(Pageable pageable, @Param("idProjeto") int idProjeto);

        @Query(value = "SELECT T.id, T.titulo, T.descricao, T.prazo, T.status, T.id_projeto, T.id_responsavel, RT.nome as nome_responsavel FROM "
                        + "TB_TAREFAS T JOIN TB_PROJETOS P ON(T.ID_PROJETO = P.ID)" +
                        "LEFT JOIN TB_RESPONSAVEL_TAREFA RT ON (T.id_responsavel = RT.id)" +
                        " WHERE T.id_projeto = :idProjeto AND T.titulo LIKE :titulo", nativeQuery = true)
        Page<TarefaDTO> getPageTarefaTitulo(Pageable pageable, @Param("idProjeto") int idProjeto,
                        @Param("titulo") String titulo);

        @Query(value = "SELECT T.id, T.titulo, T.descricao, T.prazo, T.status, T.id_projeto, T.id_responsavel, RT.nome as nome_responsavel FROM "
                        + "TB_TAREFAS T JOIN TB_PROJETOS P ON(T.ID_PROJETO = P.ID)" +
                        "LEFT JOIN TB_RESPONSAVEL_TAREFA RT ON (T.id_responsavel = RT.id)" +
                        " WHERE T.id_projeto = :idProjeto AND T.status = :status", nativeQuery = true)
        Page<TarefaDTO> getPageTarefaStatus(Pageable pageable, @Param("idProjeto") int idProjeto,
                        @Param("status") String status);

        @Query(value = "SELECT T.id, T.titulo, T.descricao, T.prazo, T.status, T.id_projeto, T.id_responsavel, RT.nome as nome_responsavel FROM "
                        + "TB_TAREFAS T JOIN TB_PROJETOS P ON(T.ID_PROJETO = P.ID)" +
                        "LEFT JOIN TB_RESPONSAVEL_TAREFA RT ON (T.id_responsavel = RT.id)" +
                        " WHERE T.id_projeto = :idProjeto AND T.titulo LIKE :titulo AND T.status = :status", nativeQuery = true)
        Page<TarefaDTO> getPageTarefaTituloStatus(Pageable pageable, @Param("idProjeto") int idProjeto,
                        @Param("titulo") String titulo, @Param("status") String status);
}
