package br.com.perinity.test.gerenciamentotarefas.model.dto.converter;

import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.PessoaMediaGastoHorasResponse;
import org.hibernate.Hibernate;

public class PessoaMediaGastoHorasConverter implements ConverterResponse<Pessoa, PessoaMediaGastoHorasResponse> {

    @Override
    public PessoaMediaGastoHorasResponse convertToResponse(Pessoa model) {
        Double mediaHorasGasto = 0.0;
        if(!model.getTarefas().isEmpty()) {
            mediaHorasGasto = (double) model.getTarefas()
                    .stream()
                    .map(tarefa -> tarefa.getDuracao())
                    .reduce((acumulador, duracaoAtual) -> acumulador + duracaoAtual)
                    .get() / (double) model.getTarefas().size();
        }
        PessoaMediaGastoHorasResponse response = PessoaMediaGastoHorasResponse.builder()
                                                    .pessoa(model)
                                                    .mediaHorasGastas(mediaHorasGasto)
                                                    .build();
        return response;
    }
}
