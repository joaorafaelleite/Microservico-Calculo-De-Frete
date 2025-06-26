package com.itaulatam.ms_calculodefrete.api.request;

import com.itaulatam.ms_calculodefrete.infrastructure.enums.Transporte;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PedidoFreteRequestDto {
    private Double pesoDoPacote;
    private Double distanciaDaEntrega;
    private Transporte tipoDeTransporte;

    public PedidoFreteRequestDto() {
    }

    public PedidoFreteRequestDto(@NotNull Double pesoDoPacote, @NotNull Double distanciaDaEntrega, @NotNull Transporte tipoDeTransporte) {
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
