package br.com.perinity.test.gerenciamentotarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TarefaAddException extends PersonRestException{

    public TarefaAddException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST.value());
    }
}
