package com.itaulatam.ms_calculodefrete.api.converter;

import com.itaulatam.ms_calculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.ms_calculodefrete.infrastructure.models.PedidoDeFrete;
import org.springframework.stereotype.Component;

@Component
public class PedidoDeFreteConverter {
    public PedidoDeFrete paraPedidoDeFrete(PedidoFreteRequestDto pedidoFreteDto){
        return new PedidoDeFrete(pedidoFreteDto.getPesoDoPacote(), pedidoFreteDto.getDistanciaDaEntrega(), pedidoFreteDto.getTipoDeTransporte());
    }
}
