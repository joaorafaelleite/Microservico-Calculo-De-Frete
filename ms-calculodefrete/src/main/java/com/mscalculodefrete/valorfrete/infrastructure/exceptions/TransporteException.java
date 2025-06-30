package com.mscalculodefrete.valorfrete.infrastructure.exceptions;

public class TransporteException extends RuntimeException{
    public TransporteException() {
        super("Tipo de frente inválido, favor selecionar um tipo de frente válido");
    }

}
