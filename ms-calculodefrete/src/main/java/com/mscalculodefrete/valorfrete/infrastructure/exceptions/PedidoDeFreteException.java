package com.mscalculodefrete.valorfrete.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoDeFreteException extends RuntimeException{
    public PedidoDeFreteException(){super("O pedido de frete é inválido, favor verificar as informações fornecidas");}
    public PedidoDeFreteException(String mensagem){
        super(mensagem);
    }
}
