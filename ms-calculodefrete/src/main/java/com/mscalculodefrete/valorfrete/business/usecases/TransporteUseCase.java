package com.mscalculodefrete.valorfrete.business.usecases;

import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class TransporteUseCase {
    private final FreteContext freteContext;
    private final Map<Transporte, FreteStrategyInterface> estrategias;

    public TransporteUseCase(
            FreteContext freteContext,
            Map<Transporte, FreteStrategyInterface> estrategias
    ) {
        this.freteContext = freteContext;
        this.estrategias = estrategias;
    }

    public void selecionarFreteStrategy(PedidoDeFrete pedidoDeFrete) {
        Transporte transporte = pedidoDeFrete.getTipoDeTransporte();

        if (isNull(transporte) || !estrategias.containsKey(transporte)) {
            throw new TransporteException();
        }

        freteContext.setFreteStrategy(estrategias.get(transporte));
    }
}
