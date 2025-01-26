package com.matheus.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
    private int id;
    private String titulo;
    private String descricao;
    private int prazo;
    private String status;
    private int id_projeto;
    private int id_responsavel;
    private String nome_responsavel;
}
