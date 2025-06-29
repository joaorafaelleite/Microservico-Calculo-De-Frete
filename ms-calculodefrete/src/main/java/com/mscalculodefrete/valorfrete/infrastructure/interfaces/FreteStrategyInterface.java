package com.mscalculodefrete.valorfrete.infrastructure.interfaces;

import java.math.BigDecimal;

public interface FreteStrategyInterface {
    BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete);

}
