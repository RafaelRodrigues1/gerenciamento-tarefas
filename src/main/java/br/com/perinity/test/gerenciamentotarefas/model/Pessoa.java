package br.com.perinity.test.gerenciamentotarefas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PESSOA")
@Table(name = "PESSOA")
public class Pessoa implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
    private Departamento departamento;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoaAlocada", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Tarefa> tarefas;
}
