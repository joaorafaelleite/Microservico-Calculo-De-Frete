package com.itaulatam.ms_calculodefrete.infrastructure.interfaces;

import java.math.BigDecimal;

public interface ServicoDeFreteStrategyInterface {
    public abstract BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete);

}
