package com.itaulatam.ms_calculodefrete.infrastructure.entities;

import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.ServicoDeFrete;

import java.math.BigDecimal;

public class FreteNormal implements ServicoDeFrete {
    private BigDecimal pesoDoPacote;
    private BigDecimal distanciaDaEntrega;
    private BigDecimal valorDoFrete;

    public FreteNormal() {
    }

    public FreteNormal(BigDecimal pesoDoPacote, BigDecimal distanciaDaEntrega, BigDecimal valorDoFrete) {
        this.pesoDoPacote = pesoDoPacote;
        this.distanciaDaEntrega = distanciaDaEntrega;
        this.valorDoFrete = valorDoFrete;
    }

    public BigDecimal getPesoDoPacote() {
        return pesoDoPacote;
    }

    public BigDecimal getDistanciaDaEntrega() {
        return distanciaDaEntrega;
    }

    public BigDecimal getValorDoFrete() {
        return valorDoFrete;
    }

    public void setPesoDoPacote(BigDecimal pesoDoPacote) {
        this.pesoDoPacote = pesoDoPacote;
    }

    public void setDistanciaDaEntrega(BigDecimal distanciaDaEntrega) {
        this.distanciaDaEntrega = distanciaDaEntrega;
    }

    @Override
    public BigDecimal calcularValorDoFrete(BigDecimal pesoDoPacote, BigDecimal distanciaDaEntrega){
        BigDecimal valorBase = BigDecimal.valueOf(5.0);

        valorDoFrete.add(valorBase);
        valorDoFrete.add(pesoDoPacote.multiply(BigDecimal.valueOf(0.5)));
        valorDoFrete.add(distanciaDaEntrega.multiply(BigDecimal.valueOf(2.0)));

        return valorDoFrete;
    }
}
