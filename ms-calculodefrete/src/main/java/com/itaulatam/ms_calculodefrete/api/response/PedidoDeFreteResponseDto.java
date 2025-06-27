package com.itaulatam.ms_calculodefrete.api.response;

import java.math.BigDecimal;

public class PedidoDeFreteResponseDto {
    private BigDecimal valorDoFrete;

    public PedidoDeFreteResponseDto() {
    }

    public PedidoDeFreteResponseDto(BigDecimal valorDoFrete) {
        this.valorDoFrete = valorDoFrete;
    }

    public BigDecimal getValorDoFrete() {
        return valorDoFrete;
    }

}
