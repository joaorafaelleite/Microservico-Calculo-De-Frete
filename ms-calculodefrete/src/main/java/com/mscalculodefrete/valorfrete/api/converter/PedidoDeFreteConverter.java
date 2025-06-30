package com.mscalculodefrete.valorfrete.api.converter;

import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.api.response.PedidoDeFreteResponseDto;
import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.springframework.stereotype.Component;

@Component
public class PedidoDeFreteConverter {
    public PedidoDeFrete paraPedidoDeFrete(PedidoFreteRequestDto requestDto, Transporte transporte) {
        return new PedidoDeFrete(
                requestDto.getPesoDoPacote(),
                requestDto.getDistanciaDaEntrega(),
                transporte
        );
    }

    public PedidoDeFreteResponseDto paraPedidoDeFreteResponseDto(PedidoDeFrete pedidoFrete, FreteContext freteContext) {
        return new PedidoDeFreteResponseDto(freteContext.calcularValorDoFrete(pedidoFrete));
    }
}
