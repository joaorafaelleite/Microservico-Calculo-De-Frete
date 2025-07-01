package com.mscalculodefrete.valorfrete.api.converter;

import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.api.response.PedidoDeFreteResponseDto;
import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoDeFreteConverterTest {

    private final PedidoDeFreteConverter converter = new PedidoDeFreteConverter();

    @Test
    void deveConverterParaPedidoDeFreteCorretamente() {
        PedidoFreteRequestDto dto = new PedidoFreteRequestDto(2.0, 20.0, "NORMAL");
        PedidoDeFrete pedido = converter.paraPedidoDeFrete(dto, Transporte.NORMAL);

        assertEquals(2.0, pedido.getPesoDoPacote());
        assertEquals(20.0, pedido.getDistanciaDaEntrega());
        assertEquals(Transporte.NORMAL, pedido.getTipoDeTransporte());
    }

    @Test
    void deveConverterParaResponseDtoCorretamente() {
        PedidoDeFrete pedido = mock(PedidoDeFrete.class);
        FreteContext context = mock(FreteContext.class);
        when(context.calcularValorDoFrete(pedido)).thenReturn(BigDecimal.valueOf(42));

        PedidoDeFreteResponseDto response = converter.paraPedidoDeFreteResponseDto(pedido, context);

        assertEquals(BigDecimal.valueOf(42), response.getValorDoFrete());
        verify(context).calcularValorDoFrete(pedido);
    }
}