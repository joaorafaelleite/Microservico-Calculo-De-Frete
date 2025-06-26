package com.itaulatam.ms_calculodefrete.infrastructure.entities;

import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.PedidoDeFreteInterface;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.ServicoDeFreteStrategyInterface;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Frete {
    private ServicoDeFreteStrategyInterface servicoDeFrete;

    public Frete() {
    }

    public Frete(ServicoDeFreteStrategyInterface servicoDeFrete) {
        this.servicoDeFrete = servicoDeFrete;
    }

    public ServicoDeFreteStrategyInterface getServicoDeFrete() {
        return servicoDeFrete;
    }

    public void setServicoDeFrete(ServicoDeFreteStrategyInterface servicoDeFrete) {
        this.servicoDeFrete = servicoDeFrete;
    }

    public BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete){
        return servicoDeFrete.calcularValorDoFrete(pedidoDeFrete);
    }
}
