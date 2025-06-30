package com.mscalculodefrete.valorfrete.api.converter;

import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.api.response.PedidoDeFreteResponseDto;
import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.ConverterException;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.springframework.stereotype.Component;

@Component
public class PedidoDeFreteConverter {
    public PedidoDeFrete paraPedidoDeFrete(PedidoFreteRequestDto requestDto) {
        Transporte transporte;

        try {
            transporte = Transporte.valueOf(requestDto.getTipoDeTransporte());
        } catch (Exception exception) {
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
