package com.itaulatam.mscalculodefrete.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ObjetoErro {
    private HttpStatus status;
    private String mensagem;

    public ObjetoErro(HttpStatus status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public ObjetoErro(HttpStatusCode statusCode, String mensagem) {
        this.status = HttpStatus.resolve(statusCode.value());
        this.mensagem = mensagem;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
