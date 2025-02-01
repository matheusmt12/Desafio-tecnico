package com.matheus.desafio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
    private int id;

    @Size(min = 5, max = 25, message = "O campo titulo tem que ter entre 5 a 25 caracteres")
    @NotBlank(message = "O campo título é requerido")
    private String titulo;

    @Size(min = 5, max = 25, message = "O campo descrição tem que ter entre 5 a 25 caracteres")
    @NotBlank(message = "O campo descrição é requerido")
    private String descricao;
   
    @Min(value = 1, message = "O numero não pode ser Menor que 1(um).")
    private int prazo;
   
    @Pattern(regexp = "^(PLANEJADO|EM EXECUÇÃO|ABORTADO|FINALIZADO)$",  message = "Esse Status não é aceito.")
    private String status;
    private int id_projeto;
    private int id_responsavel;
    private String nome_responsavel;
}
