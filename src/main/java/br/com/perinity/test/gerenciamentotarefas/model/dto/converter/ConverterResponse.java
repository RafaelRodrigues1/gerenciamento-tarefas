package br.com.perinity.test.gerenciamentotarefas.model.dto.converter;

import br.com.perinity.test.gerenciamentotarefas.model.Model;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.Response;

public interface ConverterResponse <M extends Model, R extends Response> {

    R convertToResponse(M model);
}
