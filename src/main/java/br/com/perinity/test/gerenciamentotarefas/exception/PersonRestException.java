package br.com.perinity.test.gerenciamentotarefas.exception;

import lombok.Data;

@Data
public abstract class PersonRestException extends RuntimeException{

    private int statusCode;

    public PersonRestException(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }
}
