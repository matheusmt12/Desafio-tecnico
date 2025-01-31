package com.matheus.desafio.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO {

    private int id;
    @NotBlank(message = "Campo nome é requirido")
    private String nome;
    @NotBlank(message = "Campo descrição é requirido")
    private String descricao;
    @NotNull(message = "Campo data inicio é requirido")
    private LocalDate data_inicio;
    @NotNull(message = "Campo data termino é requirido")
    private LocalDate data_termino;
    @Pattern(regexp = "^(PLANEJADO|EM EXECUÇÃO|ABORTADO|FINALIZADO)$" , message = "Esse Status não é aceito.")
    private String status;
    private int id_responsavel;
    private String nome_responsavel;
}
