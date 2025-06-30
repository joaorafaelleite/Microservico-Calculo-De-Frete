package com.mscalculodefrete.valorfrete.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDeFreteResponseDto {
    private BigDecimal valorDoFrete;

    public PedidoDeFreteResponseDto(BigDecimal valorDoFrete) {
        this.valorDoFrete = valorDoFrete;
    }

    public BigDecimal getValorDoFrete() {
        return valorDoFrete;
    }

}
