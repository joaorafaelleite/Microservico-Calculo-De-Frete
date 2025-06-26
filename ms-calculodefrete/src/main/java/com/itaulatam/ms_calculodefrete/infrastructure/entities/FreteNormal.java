package com.itaulatam.ms_calculodefrete.infrastructure.entities;

import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.PedidoDeFreteInterface;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.ServicoDeFreteStrategyInterface;

import java.math.BigDecimal;

public class FreteNormal implements ServicoDeFreteStrategyInterface {
    private PedidoDeFreteInterface pedidoDeFrete;

    public FreteNormal() {
    }

    public FreteNormal(PedidoDeFreteInterface pedidoDeFrete) {
        this.pedidoDeFrete = pedidoDeFrete;
    }


    @Override
    public BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete){
        BigDecimal valorDoFrete = BigDecimal.ZERO;

        BigDecimal valorBase = BigDecimal.valueOf(5.0);
        BigDecimal custoPorPeso = BigDecimal.valueOf(0.5);
        BigDecimal custoPorDistancia = BigDecimal.valueOf(2.0);

        BigDecimal pesoDoPacote = BigDecimal.valueOf(pedidoDeFrete.getPesoDoPacote());
        BigDecimal distanciaDaEntrega = BigDecimal.valueOf(pedidoDeFrete.getDistanciaDaEntrega());

        valorDoFrete = valorDoFrete.add(valorBase);
        valorDoFrete = valorDoFrete.add(pesoDoPacote.multiply(custoPorPeso));
        valorDoFrete = valorDoFrete.add(distanciaDaEntrega.multiply(custoPorDistancia));

        return valorDoFrete;
    }
}
