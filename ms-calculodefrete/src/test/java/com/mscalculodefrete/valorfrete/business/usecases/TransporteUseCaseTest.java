package com.mscalculodefrete.valorfrete.business.usecases;

import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransporteUseCaseTest {
    private FreteContext freteContext;
    private FreteStrategyInterface freteNormal;
    private FreteStrategyInterface freteExpresso;
    private Map<Transporte, FreteStrategyInterface> estrategias;
    private TransporteUseCase transporteUseCase;

    @BeforeEach
    void setUp() {
        freteContext = mock(FreteContext.class);
        freteNormal = mock(FreteStrategyInterface.class);
        freteExpresso = mock(FreteStrategyInterface.class);
        estrategias = new HashMap<>();
        estrategias.put(Transporte.NORMAL, freteNormal);
        estrategias.put(Transporte.EXPRESSO, freteExpresso);
        transporteUseCase = new TransporteUseCase(freteContext, estrategias);
    }

    @Test
    void selecionarFreteStrategyDeveSetarFreteNormalQuandoTransporteNormal() {
        PedidoDeFrete pedido = mock(PedidoDeFrete.class);
        when(pedido.getTipoDeTransporte()).thenReturn(Transporte.NORMAL);

        transporteUseCase.selecionarFreteStrategy(pedido);

        verify(freteContext).setFreteStrategy(freteNormal);
    }

    @Test
    void selecionarFreteStrategyDeveSetarFreteExpressoQuandoTransporteExpresso() {
        PedidoDeFrete pedido = mock(PedidoDeFrete.class);
        when(pedido.getTipoDeTransporte()).thenReturn(Transporte.EXPRESSO);

        transporteUseCase.selecionarFreteStrategy(pedido);

        verify(freteContext).setFreteStrategy(freteExpresso);
    }

    @Test
    void selecionarFreteStrategyErroTransporteNulo() {
        PedidoDeFrete pedido = mock(PedidoDeFrete.class);
        when(pedido.getTipoDeTransporte()).thenReturn(null);

        assertThrows(TransporteException.class, () -> transporteUseCase.selecionarFreteStrategy(pedido));
        verifyNoInteractions(freteContext);
    }

    @Test
    void selecionarFreteStrategyErroTransporteNaoRegistrado() {
        PedidoDeFrete pedido = mock(PedidoDeFrete.class);
        when(pedido.getTipoDeTransporte()).thenReturn(null);

        estrategias.clear();

        assertThrows(TransporteException.class, () -> transporteUseCase.selecionarFreteStrategy(pedido));
        verifyNoInteractions(freteContext);
    }
}