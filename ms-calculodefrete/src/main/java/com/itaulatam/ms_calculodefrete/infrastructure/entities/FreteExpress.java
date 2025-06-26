package com.itaulatam.ms_calculodefrete.infrastructure.entities;

import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.ServicoDeFrete;

import java.math.BigDecimal;
public class FreteExpress implements ServicoDeFrete {

    private BigDecimal pesoDoPacote;
    private BigDecimal distanciaDaEntrega;
    private BigDecimal valorDoFrete;

    public FreteExpress() {
    }

    public FreteExpress(BigDecimal pesoDoPacote, BigDecimal distanciaDaEntrega, BigDecimal valorDoFrete) {
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
        BigDecimal valorBase = BigDecimal.valueOf(10.0);

        valorDoFrete.add(valorBase);
        valorDoFrete.add(pesoDoPacote.multiply(BigDecimal.valueOf(1.0)));
        valorDoFrete.add(distanciaDaEntrega.multiply(BigDecimal.valueOf(5.0)));

        return valorDoFrete;
    }
}
