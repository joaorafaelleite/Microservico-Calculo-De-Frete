package com.itaulatam.ms_calculodefrete.infrastructure.interfaces;

import java.math.BigDecimal;

public interface ServicoDeFrete {
    public abstract BigDecimal calcularValorDoFrete(BigDecimal pesoDoPacote, BigDecimal distanciaDaEntrega);

}
