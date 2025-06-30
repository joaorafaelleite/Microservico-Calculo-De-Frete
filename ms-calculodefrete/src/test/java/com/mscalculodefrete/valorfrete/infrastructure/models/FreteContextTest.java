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
    private FreteStrategyInterface freteStrategy;
    private PedidoDeFreteInterface pedidoDeFrete;

    @BeforeEach
    void setUp() {
        freteStrategy = mock(FreteStrategyInterface.class);
        pedidoDeFrete = mock(PedidoDeFreteInterface.class);
        freteContext = new FreteContext(freteStrategy);
    }

    @Test
    void calcularValorDoFreteSucesso() {
        BigDecimal valorEsperado = BigDecimal.valueOf(100.00);
        when(freteStrategy.calcularValorDoFrete(pedidoDeFrete)).thenReturn(valorEsperado);

        BigDecimal resultado = freteContext.calcularValorDoFrete(pedidoDeFrete);

        assertEquals(valorEsperado, resultado);
        verify(freteStrategy).calcularValorDoFrete(pedidoDeFrete);
    }

    @Test
    void calcularValorDoFreteErroFreteStrategyNulo() {
        FreteContext contextoComStrategyNula = new FreteContext(null);

        assertThrows(BusinessException.class, () -> contextoComStrategyNula.calcularValorDoFrete(pedidoDeFrete));
    }

    @Test
    void calcularValorDoFreteErroPedidoNulo() {
        RuntimeException thrown = assertThrows(PedidoDeFreteException.class, () -> freteContext.calcularValorDoFrete(null));
                assertEquals("O pedido de frete é inválido, favor verificar as informações fornecidas", thrown.getMessage());
    }
}