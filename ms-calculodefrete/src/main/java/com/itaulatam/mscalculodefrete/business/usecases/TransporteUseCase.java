package com.itaulatam.mscalculodefrete.business.usecases;

import com.itaulatam.mscalculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.mscalculodefrete.infrastructure.enums.Transporte;
import com.itaulatam.mscalculodefrete.infrastructure.exceptions.TransporteException;
import com.itaulatam.mscalculodefrete.infrastructure.interfaces.FreteStrategyInterface;
import com.itaulatam.mscalculodefrete.infrastructure.models.FreteContext;
import com.itaulatam.mscalculodefrete.infrastructure.models.FreteExpresso;
import com.itaulatam.mscalculodefrete.infrastructure.models.FreteNormal;
import com.itaulatam.mscalculodefrete.infrastructure.models.PedidoDeFrete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class TransporteUseCase {
    private FreteContext freteContext;

    public TransporteUseCase() {
    }

    @Autowired
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
