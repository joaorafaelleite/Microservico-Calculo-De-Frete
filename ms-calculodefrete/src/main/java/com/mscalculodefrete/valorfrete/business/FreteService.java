package com.mscalculodefrete.valorfrete.business;

import com.mscalculodefrete.valorfrete.api.converter.PedidoDeFreteConverter;
import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.api.response.PedidoDeFreteResponseDto;
import com.mscalculodefrete.valorfrete.business.usecases.TransporteUseCase;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import org.springframework.stereotype.Service;

@Service
public class FreteService {
    private TransporteUseCase transporteUseCase;
    private PedidoDeFreteConverter pedidoDeFreteConverter;
    private FreteContext freteContext;

    public FreteService(TransporteUseCase transporteUseCase, PedidoDeFreteConverter pedidoDeFreteConverter, FreteContext freteContext){
        this.transporteUseCase = transporteUseCase;
        this.pedidoDeFreteConverter = pedidoDeFreteConverter;
        this.freteContext = freteContext;
    }

    public PedidoDeFreteResponseDto calcular(PedidoFreteRequestDto requestDto) throws Exception {
        PedidoDeFrete pedidoDeFrete = pedidoDeFreteConverter.paraPedidoDeFrete(requestDto);

        FreteStrategyInterface freteStrategy = transporteUseCase.selecionarFreteStrategy(pedidoDeFrete);

        PedidoDeFreteResponseDto responseDto = pedidoDeFreteConverter.paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContext);

        return responseDto;
    }
}
