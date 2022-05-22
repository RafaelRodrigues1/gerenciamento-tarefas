package br.com.perinity.test.gerenciamentotarefas.model.dto.converter;

import br.com.perinity.test.gerenciamentotarefas.model.Departamento;
import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import br.com.perinity.test.gerenciamentotarefas.model.Tarefa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.request.TarefaAdicaoRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TarefaAdicaoConverter implements ConverterRequest<Tarefa, TarefaAdicaoRequest> {

    @Override
    public Tarefa convertToModel(TarefaAdicaoRequest request) {
        Tarefa model = Tarefa.builder()
                            .titulo(request.getTitulo())
                            .descricao(request.getDescricao())
                            .prazo(LocalDate.parse(request.getPrazo(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                            .departamento(Departamento.builder().id(request.getDepartamento()).build())
                            .duracao(request.getDuracao())
                            .pessoaAlocada(Pessoa.builder().id(request.getPessoaAlocada()).build())
                            .finalizado(request.getFinalizado())
                            .build();
        return model;
    }
}
