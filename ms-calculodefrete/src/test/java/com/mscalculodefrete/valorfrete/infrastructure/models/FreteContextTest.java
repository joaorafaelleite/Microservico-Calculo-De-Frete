package com.mscalculodefrete.valorfrete.infrastructure.models;

import com.mscalculodefrete.valorfrete.infrastructure.exceptions.BusinessException;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.PedidoDeFreteException;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.PedidoDeFreteInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FreteContextTest {

    private FreteContext freteContext;
    private FreteStrategyInterface strategyMock;
    private PedidoDeFreteInterface pedidoMock;

    @BeforeEach
    void setUp() {
        freteContext = new FreteContext();
        strategyMock = mock(FreteStrategyInterface.class);
        pedidoMock = mock(PedidoDeFrete.class);
    }

    @Test
    void deveCalcularValorDoFreteComStrategyDefinida() {
        freteContext.setFreteStrategy(strategyMock);
        when(strategyMock.calcularValorDoFrete(pedidoMock)).thenReturn(BigDecimal.valueOf(123.45));

        BigDecimal valor = freteContext.calcularValorDoFrete(pedidoMock);

        assertEquals(BigDecimal.valueOf(123.45), valor);
        verify(strategyMock).calcularValorDoFrete(pedidoMock);
    }

    @Test
    void deveLancarExcecaoQuandoStrategyNaoDefinida() {
        pedidoMock = mock(PedidoDeFrete.class);
        freteContext = new FreteContext();

        BusinessException ex = assertThrows(BusinessException.class,
                () -> freteContext.calcularValorDoFrete(pedidoMock));
        assertTrue(ex.getMessage().toLowerCase().contains("erro ao realizar o calculo do frete"));
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNulo() {
        freteContext = new FreteContext(strategyMock);
        PedidoDeFreteException ex = assertThrows(PedidoDeFreteException.class,
                () -> freteContext.calcularValorDoFrete(null));
        assertTrue(ex.getMessage().toLowerCase().contains("pedido de frete é inválido"));
    }


    @Test
    void devePermitirTrocarStrategyEmTempoDeExecucao() {
        FreteStrategyInterface strategy1 = mock(FreteStrategyInterface.class);
        FreteStrategyInterface strategy2 = mock(FreteStrategyInterface.class);

        freteContext.setFreteStrategy(strategy1);
        when(strategy1.calcularValorDoFrete(pedidoMock)).thenReturn(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, freteContext.calcularValorDoFrete(pedidoMock));

        freteContext.setFreteStrategy(strategy2);
        when(strategy2.calcularValorDoFrete(pedidoMock)).thenReturn(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, freteContext.calcularValorDoFrete(pedidoMock));
    }
}