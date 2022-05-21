package br.com.perinity.test.gerenciamentotarefas.model.dto.converter;

import br.com.perinity.test.gerenciamentotarefas.model.Model;
import br.com.perinity.test.gerenciamentotarefas.model.dto.request.Request;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.Response;

public interface ConverterRequest <M extends Model, R extends Request> {

    M convertToModel(R request);
}
