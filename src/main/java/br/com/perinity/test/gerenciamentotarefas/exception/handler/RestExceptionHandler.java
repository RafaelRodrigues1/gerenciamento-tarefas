package br.com.perinity.test.gerenciamentotarefas.exception.handler;

import br.com.perinity.test.gerenciamentotarefas.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { PessoaNotFoundException.class, TarefaAddException.class,
            DepartamentoNotFoundException.class, TarefaNotFoundException.class })
    protected ResponseEntity<Object> handleConflict(
            PersonRestException ex, WebRequest request) {
        MensagemErro mensagemErro = new MensagemErro(ex.getMessage(), LocalDateTime.now());
        return handleExceptionInternal(ex, mensagemErro,
                new HttpHeaders(), HttpStatus.resolve(ex.getStatusCode()), request);
    }
}
