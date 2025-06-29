package com.mscalculodefrete.valorfrete.infrastructure.models;

import com.mscalculodefrete.valorfrete.infrastructure.interfaces.PedidoDeFreteInterface;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FreteExpresso implements FreteStrategyInterface {
    private  PedidoDeFreteInterface pedidoDeFrete;

    public FreteExpresso() {
    }

    public FreteExpresso(PedidoDeFreteInterface pedidoDeFrete) {
        this.pedidoDeFrete = pedidoDeFrete;
    }

    @Override
    public BigDecimal calcularValorDoFrete(PedidoDeFreteInterface pedidoDeFrete){
        BigDecimal valorDoFrete = BigDecimal.ZERO;

        BigDecimal valorBase = BigDecimal.valueOf(10.0);
        BigDecimal custoPorPeso = BigDecimal.valueOf(1.0);
        BigDecimal custoPorDistancia = BigDecimal.valueOf(5.0);

        BigDecimal pesoDoPacote = BigDecimal.valueOf(pedidoDeFrete.getPesoDoPacote());
        BigDecimal distanciaDaEntrega = BigDecimal.valueOf(pedidoDeFrete.getDistanciaDaEntrega());

        valorDoFrete = valorDoFrete.add(valorBase);
        valorDoFrete = valorDoFrete.add(pesoDoPacote.multiply(custoPorPeso));
        valorDoFrete = valorDoFrete.add(distanciaDaEntrega.multiply(custoPorDistancia));

        return valorDoFrete;
    }
}
