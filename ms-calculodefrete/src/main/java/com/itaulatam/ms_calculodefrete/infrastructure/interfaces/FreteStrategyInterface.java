package com.itaulatam.ms_calculodefrete.infrastructure.interfaces;

import java.math.BigDecimal;

public interface FreteStrategyInterface {
    public abstract void setPedidoFrete(PedidoDeFreteInterface pedidoFrete);

    public abstract BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete);

}
