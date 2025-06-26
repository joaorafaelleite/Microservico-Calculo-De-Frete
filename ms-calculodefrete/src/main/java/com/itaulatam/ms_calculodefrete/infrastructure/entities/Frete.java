package com.itaulatam.ms_calculodefrete.infrastructure.entities;

import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.ServicoDeFrete;

import java.math.BigDecimal;

public class Frete {
    ServicoDeFrete servicoDeFrete;

    public Frete() {
    }

    public Frete(ServicoDeFrete servicoDeFrete) {
        this.servicoDeFrete = servicoDeFrete;
    }

    public ServicoDeFrete getServicoDeFrete() {
        return servicoDeFrete;
    }

    public void setServicoDeFrete(ServicoDeFrete servicoDeFrete) {
        this.servicoDeFrete = servicoDeFrete;
    }

    public BigDecimal calcularValorDoFrete(BigDecimal pesoDoPacote, BigDecimal distanciaDaEntrega, ServicoDeFrete servicoDeFrete){
        return servicoDeFrete.calcularValorDoFrete(pesoDoPacote, distanciaDaEntrega);
    }
}
