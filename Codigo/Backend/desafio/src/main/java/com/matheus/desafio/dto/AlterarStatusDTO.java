package com.matheus.desafio.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlterarStatusDTO {

    @Pattern(regexp = "^(PLANEJADO|EM EXECUÇÃO|ABORTADO|FINALIZADO)$",  message = "Esse Status não é aceito.")
    private String status;
}
