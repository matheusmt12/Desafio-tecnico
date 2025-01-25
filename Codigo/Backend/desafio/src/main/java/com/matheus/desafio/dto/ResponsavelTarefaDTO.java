package com.matheus.desafio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelTarefaDTO {
    
    private int id;
    private String name;
    private String titulo;
    private String status;
}
