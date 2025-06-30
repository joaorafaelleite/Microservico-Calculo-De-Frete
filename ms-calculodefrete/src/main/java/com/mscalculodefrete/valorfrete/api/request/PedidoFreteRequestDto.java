package com.mscalculodefrete.valorfrete.api.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class PedidoFreteRequestDto {
    @NotNull(message = "É obrigatório informar o peso da encomenda a ser entregue")
    @DecimalMin(value = "0", message = "O valor não pode ser negativo")
    private Double pesoDoPacote;

    @NotNull(message = "É obrigatório informar a distância a ser percorrida na entrega")
    @DecimalMin(value = "0", message = "O valor não pode ser negativo")
    private Double distanciaDaEntrega;

    @NotNull(message = "É obrigatório informar o tipo de transporte que deseja utilizar")
    private String tipoDeTransporte;

    public PedidoFreteRequestDto() {
    }

    public PedidoFreteRequestDto(Double pesoDoPacote, Double distanciaDaEntrega, String tipoDeTransporte) {
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

    public String getTipoDeTransporte() {
        return tipoDeTransporte;
    }

    public void setPesoDoPacote(Double pesoDoPacote) {
        this.pesoDoPacote = pesoDoPacote;
    }

    public void setDistanciaDaEntrega(Double distanciaDaEntrega) {
        this.distanciaDaEntrega = distanciaDaEntrega;
    }

    public void setTipoDeTransporte(String tipoDeTransporte) {
        this.tipoDeTransporte = tipoDeTransporte;
    }
}
