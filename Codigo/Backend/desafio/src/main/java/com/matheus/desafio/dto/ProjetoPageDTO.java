package com.matheus.desafio.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoPageDTO {
    private int id;
    @NotBlank(message = "Campo nome é requirido")
    private String nome;
    @NotBlank(message = "Campo descrição é requirido")
    private String descricao;
    @NotNull(message = "Campo data inicio é requirido")
    private Date data_inicio;
    @NotNull(message = "Campo data termino é requirido")
    private Date data_termino;
    private String status;
    private int id_responsavel;
    private String nome_responsavel;
}
