package com.itaulatam.ms_calculodefrete.infrastructure.entities;

import com.itaulatam.ms_calculodefrete.infrastructure.enums.Transporte;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.PedidoDeFreteInterface;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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

    public void setPesoDoPacote(Double pesoDoPacote) {
        this.pesoDoPacote = pesoDoPacote;
    }

    public void setDistanciaDaEntrega(Double distanciaDaEntrega) {
        this.distanciaDaEntrega = distanciaDaEntrega;
    }

    public void setTipoDeTransporte(Transporte tipoDeTransporte) {
        this.tipoDeTransporte = tipoDeTransporte;
    }
}
