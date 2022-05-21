package br.com.perinity.test.gerenciamentotarefas.model.dto.converter;

import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.PessoaResponse;

public class PessoaResponseConverter implements ConverterResponse<Pessoa, PessoaResponse> {

    @Override
    public PessoaResponse convertToResponse(Pessoa model) {
        if(model == null) return null;
        PessoaResponse response = PessoaResponse.builder()
                                    .id(model.getId())
                                    .nome(model.getNome())
                                    .build();
        return response;
    }
}
