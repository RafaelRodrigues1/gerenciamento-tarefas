package br.com.perinity.test.gerenciamentotarefas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TAREFA")
@Table(name = "TAREFA")
public class Tarefa implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRAZO")
    private LocalDate prazo;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO")
    private Departamento departamento;

    @Column(name = "DURACAO")
    private Long duracao;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA")
    private Pessoa pessoaAlocada;

    @Column(name = "FINALIZADO")
    private Boolean finalizado;
}
