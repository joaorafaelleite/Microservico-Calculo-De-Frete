package com.itaulatam.ms_calculodefrete.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoDeFreteException extends RuntimeException{
    public PedidoDeFreteException(String mensagem){
        super(mensagem);
    }
    public PedidoDeFreteException(Throwable causa) {
        super(causa);
    }
    public PedidoDeFreteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
