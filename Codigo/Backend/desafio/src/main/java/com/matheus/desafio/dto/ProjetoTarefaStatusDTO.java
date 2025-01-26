package com.matheus.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoTarefaStatusDTO {
    
    private String nome_tarefa;
    private String status;
}
