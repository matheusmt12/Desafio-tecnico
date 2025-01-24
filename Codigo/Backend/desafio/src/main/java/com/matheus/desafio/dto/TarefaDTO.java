package com.matheus.desafio.dto;

import lombok.Data;

@Data
public class TarefaDTO {
    private String titulo;
    private String descricao;
    private int prazo;
    private String status;
    private int id_projeto;
    private int id_responsavel;
}
