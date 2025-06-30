package com.mscalculodefrete.valorfrete.infrastructure.models;

import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.PedidoDeFreteInterface;
import org.springframework.stereotype.Component;

@Component
public class PedidoDeFrete implements PedidoDeFreteInterface {
    private Double pesoDoPacote;
    private Double distanciaDaEntrega;
    private Transporte tipoDeTransporte;

    public PedidoDeFrete() {
    }

    public PedidoDeFrete(Double pesoDoPacote, Double distanciaDaEntrega, Transporte tipoDeTransporte) {
        this.pesoDoPacote = pesoDoPacote;
        this.distanciaDaEntrega = distanciaDaEntrega;
        this.tipoDeTransporte = tipoDeTransporte;
    }

    public Double getPesoDoPacote() {
        return pesoDoPacote;
    }

    public Double getDistanciaDaEntrega() {
        return distanciaDaEntrega;
    }

    public Transporte getTipoDeTransporte() {
        return tipoDeTransporte;
    }
}
