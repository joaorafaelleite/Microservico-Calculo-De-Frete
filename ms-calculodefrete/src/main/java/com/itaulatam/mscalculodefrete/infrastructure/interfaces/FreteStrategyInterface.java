package com.itaulatam.mscalculodefrete.infrastructure.interfaces;

import java.math.BigDecimal;

public interface FreteStrategyInterface {
    BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete);

}
