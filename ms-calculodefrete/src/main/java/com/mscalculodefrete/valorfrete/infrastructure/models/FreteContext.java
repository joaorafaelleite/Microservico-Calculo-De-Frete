package com.mscalculodefrete.valorfrete.infrastructure.models;

import com.mscalculodefrete.valorfrete.infrastructure.exceptions.BusinessException;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.PedidoDeFreteException;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.PedidoDeFreteInterface;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Component
public class FreteContext {
    private FreteStrategyInterface freteStrategy;

    public FreteContext() {
    }

    public FreteContext(FreteStrategyInterface freteStrategy) {
        this.freteStrategy = freteStrategy;
    }

    public void setFreteStrategy(FreteStrategyInterface freteStrategy) {
        this.freteStrategy = freteStrategy;
    }

    public BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete){
        if(isNull(freteStrategy)){
            throw new BusinessException();
        }

        if(isNull(pedidoDeFrete)) {
            throw new PedidoDeFreteException();
        }

        return freteStrategy.calcularValorDoFrete(pedidoDeFrete);
    }
}
