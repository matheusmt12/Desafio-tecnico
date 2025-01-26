package com.matheus.desafio.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO {

    private int id;
    private String nome;
    private String descricao;
    private Date data_inicio;
    private Date data_termino;
    private String status;
    private int id_responsavel;
    private String nome_responsavel;
}
