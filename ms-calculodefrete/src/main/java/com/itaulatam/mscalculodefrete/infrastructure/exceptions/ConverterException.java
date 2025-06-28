package com.itaulatam.mscalculodefrete.infrastructure.exceptions;

public class ConverterException extends RuntimeException {
    public ConverterException() {
        super("Erro ao tentar converter um objeto dto para um objeto concreto");
    }
    public ConverterException(String mensagem){
        super(mensagem);
    }

}
