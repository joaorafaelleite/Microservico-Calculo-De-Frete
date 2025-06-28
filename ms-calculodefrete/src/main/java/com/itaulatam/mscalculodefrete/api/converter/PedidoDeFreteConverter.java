package com.itaulatam.mscalculodefrete.api.converter;

import com.itaulatam.mscalculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.mscalculodefrete.api.response.PedidoDeFreteResponseDto;
import com.itaulatam.mscalculodefrete.infrastructure.enums.Transporte;
import com.itaulatam.mscalculodefrete.infrastructure.exceptions.TransporteException;
import com.itaulatam.mscalculodefrete.infrastructure.models.FreteContext;
import com.itaulatam.mscalculodefrete.infrastructure.models.PedidoDeFrete;
import org.springframework.stereotype.Component;

@Component
public class PedidoDeFreteConverter {
    public PedidoDeFrete paraPedidoDeFrete(PedidoFreteRequestDto requestDto) {
        Transporte transporte;

        try {
            transporte = Transporte.valueOf(requestDto.getTipoDeTransporte());
        }catch (Exception exception){
            throw new TransporteException();
        }

        PedidoDeFrete pedidoDeFrete = new PedidoDeFrete(requestDto.getPesoDoPacote(), requestDto.getDistanciaDaEntrega(), transporte);

        return pedidoDeFrete;
    }

    public PedidoDeFreteResponseDto paraPedidoDeFreteResponseDto(PedidoDeFrete pedidoFrete, FreteContext freteContext) {
        PedidoDeFreteResponseDto responseDto = new PedidoDeFreteResponseDto(freteContext.calcularValorDoFrete(pedidoFrete));

        return responseDto;
    }
}
