package com.mscalculodefrete.valorfrete.business;

import com.mscalculodefrete.valorfrete.api.converter.PedidoDeFreteConverter;
import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import com.mscalculodefrete.valorfrete.api.response.PedidoDeFreteResponseDto;
import com.mscalculodefrete.valorfrete.business.usecases.TransporteUseCase;
import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.junit.jupiter.api.*;
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
    void deveRetornarResponseDtoQuandoTudoValido() {
        PedidoFreteRequestDto requestDto = new PedidoFreteRequestDto(1.0, 10.0, "NORMAL");
        PedidoDeFrete pedidoDeFrete = mock(PedidoDeFrete.class);
        PedidoDeFreteResponseDto responseDto = new PedidoDeFreteResponseDto(java.math.BigDecimal.TEN);

        when(pedidoDeFreteConverter.paraPedidoDeFrete(requestDto, Transporte.NORMAL)).thenReturn(pedidoDeFrete);
        when(pedidoDeFreteConverter.paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContext)).thenReturn(responseDto);

        PedidoDeFreteResponseDto result = freteService.calcular(requestDto);

        assertEquals(responseDto, result);
        verify(pedidoDeFreteConverter).paraPedidoDeFrete(requestDto, Transporte.NORMAL);
        verify(transporteUseCase).selecionarFreteStrategy(pedidoDeFrete);
        verify(pedidoDeFreteConverter).paraPedidoDeFreteResponseDto(pedidoDeFrete, freteContext);
    }

    @Test
    void deveLancarTransporteExceptionQuandoTipoTransporteInvalido() {
        PedidoFreteRequestDto requestDto = new PedidoFreteRequestDto(1.0, 10.0, "INVALIDO");

        assertThrows(TransporteException.class, () -> freteService.calcular(requestDto));
        verifyNoInteractions(pedidoDeFreteConverter, transporteUseCase, freteContext);
    }

    @Test
    void deveLancarTransporteExceptionQuandoTipoTransporteNulo() {
        PedidoFreteRequestDto requestDto = new PedidoFreteRequestDto(1.0, 10.0, null);

        assertThrows(TransporteException.class, () -> freteService.calcular(requestDto));
        verifyNoInteractions(pedidoDeFreteConverter, transporteUseCase, freteContext);
    }
}