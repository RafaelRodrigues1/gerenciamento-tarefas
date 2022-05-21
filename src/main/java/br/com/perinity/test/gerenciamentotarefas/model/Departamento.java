package br.com.perinity.test.gerenciamentotarefas.model;

import br.com.perinity.test.gerenciamentotarefas.model.dto.response.Response;
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
@Entity(name = "DEPARTAMENTO")
@Table(name = "DEPARTAMENTO")
public class Departamento implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @JsonIgnore
    @OneToMany(mappedBy = "departamento", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Tarefa> tarefas;

    @JsonIgnore
    @OneToMany(mappedBy = "departamento", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Pessoa> pessoas;

    public interface DepartamentoQtdPessoaTarefaResponse extends Response {
        Long getId();
        String getTitulo();
        Long getQuantidadePessoas();
        Long getQuantidadeTarefas();
    }
}
