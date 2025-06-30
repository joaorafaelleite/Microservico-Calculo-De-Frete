package com.mscalculodefrete.valorfrete.business;

import com.mscalculodefrete.valorfrete.api.converter.PedidoDeFreteConverter;
import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.business.usecases.TransporteUseCase;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.ConverterException;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FreteServiceTest {

    @Mock
    private TransporteUseCase transporteUseCase;

    @Mock
    private PedidoDeFreteConverter pedidoDeFreteConverter;

    @Mock
    private FreteContext freteContext;

    @InjectMocks
    private FreteService freteService;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) closeable.close();
    }

    @Test
    void calcularTesteCasoDeErroDeAoConverterRequest() {
        PedidoFreteRequestDto requestDto = new PedidoFreteRequestDto();

        when(pedidoDeFreteConverter.paraPedidoDeFrete(requestDto))
                .thenThrow(new ConverterException("Erro ao tentar converter um objeto requestDto para um objeto concreto"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                freteService.calcular(requestDto)
        );

        assertEquals("Erro ao tentar converter um objeto requestDto para um objeto concreto", thrown.getMessage());
        verify(pedidoDeFreteConverter).paraPedidoDeFrete(requestDto);
        verifyNoMoreInteractions(transporteUseCase, freteContext);
    }
}