package com.itaulatam.ms_calculodefrete.infrastructure.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String mensagem){
        super(mensagem);
    }
    public BusinessException(Throwable causa) {
        super(causa);
    }
    public BusinessException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
