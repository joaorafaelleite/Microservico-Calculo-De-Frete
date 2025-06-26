package com.itaulatam.ms_calculodefrete.business;

import com.itaulatam.ms_calculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.ms_calculodefrete.api.response.PedidoDeFreteResponseDto;
import com.itaulatam.ms_calculodefrete.infrastructure.entities.Frete;
import com.itaulatam.ms_calculodefrete.infrastructure.entities.FreteExpress;
import com.itaulatam.ms_calculodefrete.infrastructure.entities.FreteNormal;
import com.itaulatam.ms_calculodefrete.infrastructure.entities.PedidoDeFrete;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.ServicoDeFreteStrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreteService {
    private Frete frete;
    private ServicoDeFreteStrategyInterface servicoDeFrete;

    @Autowired
    public FreteService(Frete frete){
        this.frete = frete;
    }

    public PedidoDeFreteResponseDto calcular(PedidoFreteRequestDto requestDto){
        PedidoDeFrete pedidoDeFrete = new PedidoDeFrete(requestDto.getPesoDoPacote(), requestDto.getDistanciaDaEntrega(), requestDto.getTipoDeTransporte());

        switch (requestDto.getTipoDeTransporte()){
            case NORMAL -> servicoDeFrete = new FreteNormal(pedidoDeFrete);
            case EXPRESSO -> servicoDeFrete = new FreteExpress(pedidoDeFrete);
        }

        frete.setServicoDeFrete(servicoDeFrete);

        PedidoDeFreteResponseDto responseDto = new PedidoDeFreteResponseDto(frete.calcularValorDoFrete(pedidoDeFrete));

        return responseDto;
    }
}
