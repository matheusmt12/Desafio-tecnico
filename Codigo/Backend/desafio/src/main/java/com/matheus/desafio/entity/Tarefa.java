package com.matheus.desafio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id; 
    @Column(name = "titulo", length = 25, nullable= false)
    private String titulo;
    @Column(name = "descricao", length = 100 , nullable = false)
    private String descricao;
    @Column(name = "prazo", nullable = false)
    private int prazo;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_projeto", nullable = false)
    private  Projeto projeto;
    
    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private ResponsavelTarefa responsavel_tarefa;

}
