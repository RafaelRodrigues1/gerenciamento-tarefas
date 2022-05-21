package br.com.perinity.test.gerenciamentotarefas.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequest implements Request{

    private String nome;

    private Long departamento;
}
