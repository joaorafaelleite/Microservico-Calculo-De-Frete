package com.mscalculodefrete.valorfrete.business.usecases;

import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransporteUseCaseTest {
    @Mock
    private FreteContext freteContext;

    private TransporteUseCase transporteUseCase;

    @BeforeEach
    void setUp() {
        freteContext = mock(FreteContext.class);
        transporteUseCase = new TransporteUseCase(freteContext);
    }
    @Test
    void selecionarFreteStrategyDeveSetarFreteNormalQuandoTransporteNormal() {
        PedidoDeFrete pedido = mock(PedidoDeFrete.class);
        when(pedido.getTipoDeTransporte()).thenReturn(Transporte.NORMAL);

        transporteUseCase.selecionarFreteStrategy(pedido);

        verify(freteContext).setFreteStrategy(isA(com.mscalculodefrete.valorfrete.infrastructure.models.FreteNormal.class));
    }

    @Test
    void selecionarFreteStrategyDeveSetarFreteExpressoQuandoTransporteExpresso() {
        PedidoDeFrete pedido = mock(PedidoDeFrete.class);
        when(pedido.getTipoDeTransporte()).thenReturn(Transporte.EXPRESSO);

        transporteUseCase.selecionarFreteStrategy(pedido);

        verify(freteContext).setFreteStrategy(isA(com.mscalculodefrete.valorfrete.infrastructure.models.FreteExpresso.class));
    }

    @Test
    void selecionarFreteStrategyErroTransporteNulo() {
        PedidoDeFrete pedido = mock(PedidoDeFrete.class);
        when(pedido.getTipoDeTransporte()).thenReturn(null);

        assertThrows(TransporteException.class, () -> transporteUseCase.selecionarFreteStrategy(pedido));
        verifyNoInteractions(freteContext);
    }

}