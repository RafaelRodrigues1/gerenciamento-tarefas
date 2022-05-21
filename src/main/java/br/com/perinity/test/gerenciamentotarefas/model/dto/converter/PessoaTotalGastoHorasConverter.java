package br.com.perinity.test.gerenciamentotarefas.model.dto.converter;

import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.PessoaTotalGastoHorasResponse;

public class PessoaTotalGastoHorasConverter  implements ConverterResponse<Pessoa, PessoaTotalGastoHorasResponse> {

    @Override
    public PessoaTotalGastoHorasResponse convertToResponse(Pessoa model) {
        Long totalGastoHoras = 0L;
        if(!model.getTarefas().isEmpty()) {
            totalGastoHoras = model.getTarefas()
                    .stream()
                    .map(tarefa -> tarefa.getDuracao())
                    .filter(duracao -> duracao != null)
                    .reduce((acumulador, duracaoAtual) -> acumulador + duracaoAtual)
                    .get();
        }
        PessoaTotalGastoHorasResponse response = PessoaTotalGastoHorasResponse.builder()
                                                    .nome(model.getNome())
                                                    .departamento(model.getDepartamento())
                                                    .totalHorasGastas(totalGastoHoras)
                                                    .build();
        return response;
    }
}
