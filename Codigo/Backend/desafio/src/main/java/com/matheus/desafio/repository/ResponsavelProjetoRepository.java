package com.matheus.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheus.desafio.dto.ResponsavelProjetoDTO;
import com.matheus.desafio.entity.ResponsavelProjeto;

@Repository
public interface ResponsavelProjetoRepository  extends JpaRepository<ResponsavelProjeto, Integer>{


    @Query( value = "SELECT id ,nome FROM TB_RESPONSAVEL_PROJETO" , nativeQuery = true)
    List<ResponsavelProjetoDTO> getAll();

    
    @Query(value = "SELECT id, nome FROM TB_RESPONSAVEL_PROJETO WHERE id = :id  LIMIT 1", nativeQuery = true)
    ResponsavelProjetoDTO findById(@Param("id") int id);
}
