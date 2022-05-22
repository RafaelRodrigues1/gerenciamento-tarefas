package br.com.perinity.test.gerenciamentotarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TarefaNotFoundException extends PersonRestException{

    public TarefaNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND.value());
    }
}