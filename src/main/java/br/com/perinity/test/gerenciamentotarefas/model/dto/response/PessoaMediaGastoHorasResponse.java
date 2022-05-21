package br.com.perinity.test.gerenciamentotarefas.model.dto.response;

import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaMediaGastoHorasResponse implements Response {

    private Pessoa pessoa;

    private Double mediaHorasGastas;
}
