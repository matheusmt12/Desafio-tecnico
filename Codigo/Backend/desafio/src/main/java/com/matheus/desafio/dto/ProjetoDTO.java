package com.matheus.desafio.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO {

    private int id;
    @Size(min = 5, max = 25, message = "O campo nome tem que ter entre 5 a 25 caracteres")
    @NotBlank(message = "Campo nome é requirido")
    private String nome;
 
    @Size(min = 10, max = 200, message = "O camo descrição tem que ter entre 10 a 200 caracteres")
    @NotBlank(message = "Campo descrição é requirido")
    private String descricao;
 
    @NotNull(message = "Campo data inicio é requirido")
    private LocalDate data_inicio;
 
    @NotNull(message = "Campo data término é requirido")
    private LocalDate data_termino;
 
    @Pattern(regexp = "^(PLANEJADO|EM EXECUÇÃO|ABORTADO|FINALIZADO)$", message = "Esse Status não é aceito.")
    private String status;
    private int id_responsavel;
    private String nome_responsavel;
}
