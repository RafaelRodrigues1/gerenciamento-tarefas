package br.com.perinity.test.gerenciamentotarefas.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MensagemErro {

    private String mensagemErro;
    private String momento;

    public MensagemErro(String mensagemErro, LocalDateTime momentoDate) {
        this.mensagemErro = mensagemErro;
        this.momento = momentoDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
