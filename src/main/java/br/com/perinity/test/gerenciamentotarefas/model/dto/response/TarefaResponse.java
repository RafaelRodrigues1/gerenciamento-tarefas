package br.com.perinity.test.gerenciamentotarefas.model.dto.response;

import br.com.perinity.test.gerenciamentotarefas.model.Departamento;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class TarefaResponse implements Response{

    private Long id;

    private String titulo;

    private String descricao;

    private String prazo;

    private Departamento departamento;

    private Long duracao;

    private PessoaResponse pessoaAlocada;

    private Boolean finalizado;
}
