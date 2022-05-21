package br.com.perinity.test.gerenciamentotarefas.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaResponse implements Response{

    private Long id;

    private String nome;
}
