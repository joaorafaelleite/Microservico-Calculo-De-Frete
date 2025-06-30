package com.mscalculodefrete.valorfrete.business;

import com.mscalculodefrete.valorfrete.api.converter.PedidoDeFreteConverter;
import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.api.response.PedidoDeFreteResponseDto;
import com.mscalculodefrete.valorfrete.business.usecases.TransporteUseCase;
import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
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

    public PedidoDeFreteResponseDto calcular(PedidoFreteRequestDto requestDto) {
        Transporte transporte;

        try {
            transporte = Transporte.valueOf(requestDto.getTipoDeTransporte());
        } catch (Exception exception) {
            throw new TransporteException();
        }

        PedidoDeFrete pedidoDeFrete = pedidoDeFreteConverter.paraPedidoDeFrete(requestDto, transporte);

        transporteUseCase.selecionarFreteStrategy(pedidoDeFrete);

        return pedidoDeFreteConverter.paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContext);
    }
}
