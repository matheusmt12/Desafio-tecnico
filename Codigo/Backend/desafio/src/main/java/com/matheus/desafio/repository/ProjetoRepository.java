package com.matheus.desafio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheus.desafio.entity.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

        @Query(value = "SELECT * FROM TB_PROJETOS", nativeQuery = true)
        List<Projeto> getProjetos();

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
        void alterarStatus(@Param("id") int id,@Param("status") String status);

        @Query("SELECT P.id FROM Projeto P  WHERE P.id = :id")
        Integer getByID(@Param("id") int id);


}
