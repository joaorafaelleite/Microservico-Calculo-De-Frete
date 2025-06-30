package com.mscalculodefrete.valorfrete.business.usecases;

import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransporteUseCaseTest {

    private FreteContext freteContext;
    private TransporteUseCase transporteUseCase;

    @BeforeEach
    void setUp() {
        freteContext = mock(FreteContext.class);
        transporteUseCase = new TransporteUseCase(freteContext);
    }

    @Test
    void selecionarFreteStrategyFreteNormal() {
        PedidoDeFrete pedido = new PedidoDeFrete(100.00,100.00,null);
        pedido.setTipoDeTransporte(Transporte.NORMAL);

        FreteStrategyInterface strategy = transporteUseCase.selecionarFreteStrategy(pedido);

        assertNotNull(strategy);
        assertEquals("FreteNormal", strategy.getClass().getSimpleName());
        verify(freteContext).setFreteStrategy(strategy);
    }

    @Test
    void selecionarFreteStrategyFreteExpresso() {
        PedidoDeFrete pedido = new PedidoDeFrete(100.00,100.00,null);
        pedido.setTipoDeTransporte(Transporte.EXPRESSO);

        FreteStrategyInterface strategy = transporteUseCase.selecionarFreteStrategy(pedido);

        assertNotNull(strategy);
        assertEquals("FreteExpresso", strategy.getClass().getSimpleName());
        verify(freteContext).setFreteStrategy(strategy);
    }

    @Test
    void selecionarFreteStrategyErroTransporteNulo() {
        PedidoDeFrete pedido = new PedidoDeFrete();
        pedido.setTipoDeTransporte(null);

        assertThrows(TransporteException.class, () -> transporteUseCase.selecionarFreteStrategy(pedido));
        verifyNoInteractions(freteContext);
    }

    @Test
    void selecionarFreteStrategyErroTransporteInvalido() {
        PedidoDeFrete pedido = new PedidoDeFrete() {
            @Override
            public Transporte getTipoDeTransporte() {
                return null;
            }
        };

        assertThrows(TransporteException.class, () -> transporteUseCase.selecionarFreteStrategy(pedido));
        verifyNoInteractions(freteContext);
    }
}