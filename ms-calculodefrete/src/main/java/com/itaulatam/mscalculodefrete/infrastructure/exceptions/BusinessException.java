package com.itaulatam.mscalculodefrete.infrastructure.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(){super("Erro ao realizar o calculo do frete");}
    public BusinessException(String mensagem){
        super(mensagem);
    }

}
