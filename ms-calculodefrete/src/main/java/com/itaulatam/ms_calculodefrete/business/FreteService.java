package com.itaulatam.ms_calculodefrete.business;

import com.itaulatam.ms_calculodefrete.api.converter.PedidoDeFreteConverter;
import com.itaulatam.ms_calculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.ms_calculodefrete.api.response.PedidoDeFreteResponseDto;
import com.itaulatam.ms_calculodefrete.business.usecases.TipoDeTransporte;
import com.itaulatam.ms_calculodefrete.infrastructure.exceptions.BusinessException;
import com.itaulatam.ms_calculodefrete.infrastructure.models.FreteContext;
import com.itaulatam.ms_calculodefrete.infrastructure.models.PedidoDeFrete;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.FreteStrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreteService {
    private TipoDeTransporte tipoDeTransporte;
    private PedidoDeFreteConverter pedidoDeFreteConverter;
    private FreteContext freteContext;

    @Autowired
    public FreteService(TipoDeTransporte tipoDeTransporte, PedidoDeFreteConverter pedidoDeFreteConverter, FreteContext freteContext){
        this.tipoDeTransporte = tipoDeTransporte;
        this.pedidoDeFreteConverter = pedidoDeFreteConverter;
        this.freteContext = freteContext;
    }

    public PedidoDeFreteResponseDto calcular(PedidoFreteRequestDto requestDto){
        try {
            FreteStrategyInterface freteStrategy = tipoDeTransporte.selecionarFreteStrategy(requestDto);

            PedidoDeFrete pedidoDeFrete = pedidoDeFreteConverter.paraPedidoDeFrete(requestDto);

            freteStrategy.setPedidoFrete(pedidoDeFrete);

            PedidoDeFreteResponseDto responseDto = new PedidoDeFreteResponseDto(freteContext.calcularValorDoFrete(pedidoDeFrete));

            return responseDto;
        } catch (Exception e){
            throw new BusinessException("Erro ao realizar o calculo do frete", e);
        }
    }
}
