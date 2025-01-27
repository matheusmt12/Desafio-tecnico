package com.matheus.desafio.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheus.desafio.dto.ProjetoDTO;
import com.matheus.desafio.dto.ProjetoTarefaStatusDTO;
import com.matheus.desafio.entity.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

        @Query(value = "SELECT P.id, P.nome, P.descricao, P.data_inicio, P.data_termino,P.status,P.id_responsavel, RR.nome as nome_responsavel"
                        +
                        " FROM TB_PROJETOS P JOIN TB_RESPONSAVEL_PROJETO RR ON (P.ID_RESPONSAVEL = RR.ID)", nativeQuery = true)
        List<ProjetoDTO> getProjetos();

        @Modifying
        @Query(value = "INSERT INTO TB_PROJETOS (nome,descricao, data_inicio, data_termino, status, id_responsavel) VALUES"
                        +
                        "(:nome,:descricao ,:data_inicio, :data_termino,:status, :id_responsavel)", nativeQuery = true)

        void insertProjeto(@Param("nome") String nome,
                        @Param("descricao") String descricao,
                        @Param("data_inicio") Date data_inicio,
                        @Param("data_termino") Date data_termino,
                        @Param("status") String status,
                        @Param("id_responsavel") int id_responsavel);

        @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)

        Integer getLastInsertId();

        @Modifying
        @Query(value = "UPDATE TB_PROJETOS SET STATUS = :status WHERE ID = :id", nativeQuery = true)
        void alterarStatus(@Param("id") int id, @Param("status") String status);

        @Query(value = "SELECT id FROM TB_PROJETOS WHERE id = :id", nativeQuery = true)
        Optional<Integer> getByID(@Param("id") int id);

        @Query(value = "SELECT T.titulo as nome_tarefa, T.status FROM TB_PROJETOS P JOIN TB_TAREFAS T ON (P.ID = T.ID_PROJETO)"
                        +
                        "WHERE T.STATUS != 'FINALIZADO' AND T.STATUS != 'ABORTADO' AND P.ID = :id LIMIT 1", nativeQuery = true)
        Optional<ProjetoTarefaStatusDTO> vericarStatus(@Param("id") int id);

        @Query(value = "SELECT P.id, P.nome, P.descricao, P.data_inicio, P.data_termino,P.status,P.id_responsavel, RR.nome as nome_responsavel"
                        +
                        " FROM TB_PROJETOS P JOIN TB_RESPONSAVEL_PROJETO RR ON (P.ID_RESPONSAVEL = RR.ID)", nativeQuery = true)
        Page<ProjetoDTO> getProjetosPage(Pageable pageable);

        @Query(value = "SELECT P.id, P.nome, P.descricao, P.data_inicio, P.data_termino,P.status,P.id_responsavel, RR.nome as nome_responsavel"
                        +
                        " FROM TB_PROJETOS P JOIN TB_RESPONSAVEL_PROJETO RR ON (P.ID_RESPONSAVEL = RR.ID) WHERE P.nome LIKE :nome", nativeQuery = true)
        Page<ProjetoDTO> getProjetosPageNome(Pageable pageable, String nome);

        @Query(value = "SELECT P.id, P.nome, P.descricao, P.data_inicio, P.data_termino,P.status,P.id_responsavel, RR.nome as nome_responsavel"
                        +
                        " FROM TB_PROJETOS P JOIN TB_RESPONSAVEL_PROJETO RR ON (P.ID_RESPONSAVEL = RR.ID) WHERE P.status = :status", nativeQuery = true)
        Page<ProjetoDTO> getProjetosPageStatus(Pageable pageable, String status);

        @Query(value = "SELECT P.id, P.nome, P.descricao, P.data_inicio, P.data_termino,P.status,P.id_responsavel, RR.nome as nome_responsavel"
                        +
                        " FROM TB_PROJETOS P JOIN TB_RESPONSAVEL_PROJETO RR ON (P.ID_RESPONSAVEL = RR.ID) WHERE P.nome LIKE :nome AND P.status = :status", nativeQuery = true)
        Page<ProjetoDTO> getProjetosPageNomeStatus(Pageable pageable, String status, String nome);
}
