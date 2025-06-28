package com.itaulatam.mscalculodefrete.business;

import com.itaulatam.mscalculodefrete.api.converter.PedidoDeFreteConverter;
import com.itaulatam.mscalculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.mscalculodefrete.api.response.PedidoDeFreteResponseDto;
import com.itaulatam.mscalculodefrete.business.usecases.TransporteUseCase;
import com.itaulatam.mscalculodefrete.infrastructure.models.FreteContext;
import com.itaulatam.mscalculodefrete.infrastructure.models.PedidoDeFrete;
import com.itaulatam.mscalculodefrete.infrastructure.interfaces.FreteStrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
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
