package br.com.perinity.test.gerenciamentotarefas.model.dto.converter;

import br.com.perinity.test.gerenciamentotarefas.model.Tarefa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.TarefaResponse;

import java.time.format.DateTimeFormatter;

public class TarefaResponseConverter implements ConverterResponse<Tarefa, TarefaResponse> {

    @Override
    public TarefaResponse convertToResponse(Tarefa model) {
        if(model == null) return null;
        TarefaResponse response = TarefaResponse.builder()
                .id(model.getId())
                .titulo(model.getTitulo())
                .descricao(model.getDescricao())
                .duracao(model.getDuracao())
                .departamento(model.getDepartamento())
                .finalizado(model.getFinalizado())
                .prazo(model.getPrazo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .pessoaAlocada(new PessoaResponseConverter().convertToResponse(model.getPessoaAlocada()))
                .build();
        return response;
    }
}
