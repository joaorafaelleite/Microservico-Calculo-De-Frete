package com.mscalculodefrete.valorfrete.business.usecases;

import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.exceptions.TransporteException;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteContext;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteExpresso;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteNormal;
import com.mscalculodefrete.valorfrete.infrastructure.models.PedidoDeFrete;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class TransporteUseCase {
    private FreteContext freteContext;

    public TransporteUseCase(FreteContext freteContext) {
        this.freteContext = freteContext;
    }

    public FreteStrategyInterface selecionarFreteStrategy(PedidoDeFrete pedidoDeFrete) {
        Transporte transporte = pedidoDeFrete.getTipoDeTransporte();

        if(isNull(transporte)){
            throw new TransporteException();
        }

        FreteStrategyInterface freteStrategy = switch (transporte) {
            case NORMAL -> new FreteNormal();
            case EXPRESSO -> new FreteExpresso();
            default -> throw new TransporteException();
        };

        freteContext.setFreteStrategy(freteStrategy);

        return freteStrategy;
    }

}
