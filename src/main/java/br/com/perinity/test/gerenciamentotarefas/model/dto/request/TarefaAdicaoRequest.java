package br.com.perinity.test.gerenciamentotarefas.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaAdicaoRequest implements Request {

    private Long id;

    private String titulo;

    private String descricao;

    private LocalDate prazo;

    private Long departamento;

    private Long duracao;

    private Long pessoaAlocada;

    private Boolean finalizado;
}
