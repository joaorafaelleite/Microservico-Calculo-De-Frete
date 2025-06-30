package com.mscalculodefrete.valorfrete.api.converter;

import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoDeFreteConverterTest {

    private PedidoDeFreteConverter converter;

    @BeforeEach
    void setUp() {
        converter = new PedidoDeFreteConverter();
    }

    @Test
    void paraPedidoDeFreteSucesso() {
        PedidoFreteRequestDto dto = new PedidoFreteRequestDto();
        dto.setPesoDoPacote(10.0);
        dto.setDistanciaDaEntrega(100.0);
        dto.setTipoDeTransporte("NORMAL");

        var pedidoDeFrete = converter.paraPedidoDeFrete(dto);

        assertNotNull(pedidoDeFrete);
        assertEquals(10.0, pedidoDeFrete.getPesoDoPacote());
        assertEquals(100.0, pedidoDeFrete.getDistanciaDaEntrega());
        assertEquals(Transporte.NORMAL, pedidoDeFrete.getTipoDeTransporte());
    }

    @Test
    void paraPedidoDeFreteErroTransporteInvalido() {
        PedidoFreteRequestDto dto = new PedidoFreteRequestDto(10.0,100.00, "INVALIDO");

        RuntimeException thrown = assertThrows(TransporteException.class, () -> converter.paraPedidoDeFrete(dto));

        assertEquals("Tipo de frente inválido, favor selecionar um tipo de frente válido", thrown.getMessage());
    }

    @Test
    void paraPedidoDeFreteResponseDtoSucesso() {
        PedidoDeFrete pedidoDeFrete = new PedidoDeFrete(10.0, 100.0, Transporte.NORMAL);

        var freteContextMock = mock(FreteContext.class);
        when(freteContextMock.calcularValorDoFrete(pedidoDeFrete)).thenReturn(BigDecimal.valueOf(123.45));

        var responseDto = converter.paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContextMock);

        assertNotNull(responseDto);
        assertEquals(BigDecimal.valueOf(123.45), responseDto.getValorDoFrete());
    }
}