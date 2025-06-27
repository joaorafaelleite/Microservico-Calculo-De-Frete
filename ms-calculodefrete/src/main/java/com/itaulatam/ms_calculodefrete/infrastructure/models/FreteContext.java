package com.itaulatam.ms_calculodefrete.infrastructure.models;

import com.itaulatam.ms_calculodefrete.infrastructure.exceptions.BusinessException;
import com.itaulatam.ms_calculodefrete.infrastructure.exceptions.PedidoDeFreteException;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.PedidoDeFreteInterface;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.FreteStrategyInterface;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FreteContext {
    private FreteStrategyInterface freteStrategy;

    public FreteContext() {
    }

    public FreteContext(FreteStrategyInterface freteStrategy) {
        this.freteStrategy = freteStrategy;
    }

    public FreteStrategyInterface getServicoDeFrete() {
        return freteStrategy;
    }

    public void setFreteStrategy(FreteStrategyInterface freteStrategy) {
        this.freteStrategy = freteStrategy;
    }

    public BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete){
        try {
            if(pedidoDeFrete == null) {
                throw new PedidoDeFreteException("O pedido de frete não foi encontrado");
            }

            return freteStrategy.calcularValorDoFrete(pedidoDeFrete);
        }catch (Exception e){
            throw new BusinessException("Erro ao realizar o calculo do frete", e);
        }

    }
}
