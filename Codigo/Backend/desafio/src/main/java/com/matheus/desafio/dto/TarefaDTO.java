package com.matheus.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
    private int id;
    @NotBlank(message = "O campo título é requerido")
    private String titulo;
    @NotBlank(message = "O campo descrição é requerido")
    private String descricao;
    private int prazo;
    private String status;
    private int id_projeto;
    private int id_responsavel;
    private String nome_responsavel;
}
