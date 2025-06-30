package com.mscalculodefrete.valorfrete.business;

import com.mscalculodefrete.valorfrete.api.converter.PedidoDeFreteConverter;
import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.api.response.PedidoDeFreteResponseDto;
import com.mscalculodefrete.valorfrete.business.usecases.TransporteUseCase;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.ConverterException;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

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
    void calcularTesteCasoDeSucesso() throws Exception {
        PedidoFreteRequestDto requestDto = new PedidoFreteRequestDto();
        PedidoDeFrete pedidoDeFrete = new PedidoDeFrete();
        PedidoDeFreteResponseDto expectedResponse = new PedidoDeFreteResponseDto();
        FreteStrategyInterface mockStrategy = mock(FreteStrategyInterface.class);

        when(pedidoDeFreteConverter.paraPedidoDeFrete(requestDto)).thenReturn(pedidoDeFrete);
        when(transporteUseCase.selecionarFreteStrategy(pedidoDeFrete)).thenReturn(mockStrategy);
        when(pedidoDeFreteConverter.paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContext)).thenReturn(expectedResponse);

        PedidoDeFreteResponseDto result = freteService.calcular(requestDto);

        assertEquals(expectedResponse, result);
        verify(pedidoDeFreteConverter).paraPedidoDeFrete(requestDto);
        verify(transporteUseCase).selecionarFreteStrategy(pedidoDeFrete);
        verify(pedidoDeFreteConverter).paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContext);
    }

    @Test
    void calcularTesteCasoDeErroDeAoConverterRequest() throws Exception {
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

    @Test
    void calcularTesteCasoDeErroAoSelecionarFreteStrategy() throws Exception {
        PedidoFreteRequestDto requestDto = new PedidoFreteRequestDto();
        PedidoDeFrete pedidoDeFrete = new PedidoDeFrete();

        when(pedidoDeFreteConverter.paraPedidoDeFrete(requestDto)).thenReturn(pedidoDeFrete);
        when(transporteUseCase.selecionarFreteStrategy(pedidoDeFrete))
                .thenThrow(new TransporteException());

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                freteService.calcular(requestDto)
        );

        assertEquals("Tipo de frente inválido, favor selecionar um tipo de frente válido", thrown.getMessage());
        verify(pedidoDeFreteConverter).paraPedidoDeFrete(requestDto);
        verify(transporteUseCase).selecionarFreteStrategy(pedidoDeFrete);
        verifyNoMoreInteractions(freteContext);
    }

    @Test
    void calcularTesteCasoDeErroAoConverterParaResponse() throws Exception {
        PedidoFreteRequestDto requestDto = new PedidoFreteRequestDto();
        PedidoDeFrete pedidoDeFrete = new PedidoDeFrete();
        FreteStrategyInterface mockStrategy = mock(FreteStrategyInterface.class);

        when(pedidoDeFreteConverter.paraPedidoDeFrete(requestDto)).thenReturn(pedidoDeFrete);
        when(transporteUseCase.selecionarFreteStrategy(pedidoDeFrete)).thenReturn(mockStrategy);
        when(pedidoDeFreteConverter.paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContext))
                .thenThrow(new TransporteException("Erro ao tentar converter um objeto concreto para um responseDto"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                freteService.calcular(requestDto)
        );

        assertEquals("Erro ao tentar converter um objeto concreto para um responseDto", thrown.getMessage());
        verify(pedidoDeFreteConverter).paraPedidoDeFrete(requestDto);
        verify(transporteUseCase).selecionarFreteStrategy(pedidoDeFrete);
        verify(pedidoDeFreteConverter).paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContext);
    }

}