package com.matheus.desafio.entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;
    @Column(name = "data_inicio",nullable = false)
    private Date data_inicio;
    @Column(name = "data_termino", nullable = false)
    private Date data_termino;
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false)
    private ResponsavelProjeto responsavel_projeto;

    @OneToMany(mappedBy = "projeto")
    private List<Tarefa> tarafas;
}
