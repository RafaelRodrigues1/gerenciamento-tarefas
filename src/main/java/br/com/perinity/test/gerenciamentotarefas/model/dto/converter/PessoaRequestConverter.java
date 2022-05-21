package br.com.perinity.test.gerenciamentotarefas.model.dto.converter;

import br.com.perinity.test.gerenciamentotarefas.model.Departamento;
import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.request.PessoaRequest;

public class PessoaRequestConverter implements ConverterRequest<Pessoa, PessoaRequest> {

    @Override
    public Pessoa convertToModel(PessoaRequest request) {
        Pessoa model = Pessoa.builder()
                            .nome(request.getNome())
                            .departamento(Departamento.builder().id(request.getDepartamento()).build())
                            .build();
        return model;
    }
}
