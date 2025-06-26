package com.itaulatam.ms_calculodefrete.infrastructure.interfaces;

import com.itaulatam.ms_calculodefrete.infrastructure.enums.Transporte;

public interface PedidoDeFreteInterface {
    public abstract Double getPesoDoPacote();

    public abstract Double getDistanciaDaEntrega();

    public abstract Transporte getTipoDeTransporte() ;
}
