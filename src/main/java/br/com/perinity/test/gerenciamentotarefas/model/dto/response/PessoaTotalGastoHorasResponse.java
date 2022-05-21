package br.com.perinity.test.gerenciamentotarefas.model.dto.response;

import br.com.perinity.test.gerenciamentotarefas.model.Departamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaTotalGastoHorasResponse implements Response {

    private String nome;

    private Departamento departamento;

    private Long totalHorasGastas;
}
