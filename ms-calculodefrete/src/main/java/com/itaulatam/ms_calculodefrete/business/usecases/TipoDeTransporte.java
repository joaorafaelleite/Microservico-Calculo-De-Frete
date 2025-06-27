package com.itaulatam.ms_calculodefrete.business.usecases;

import com.itaulatam.ms_calculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.FreteStrategyInterface;
import com.itaulatam.ms_calculodefrete.infrastructure.models.FreteContext;
import com.itaulatam.ms_calculodefrete.infrastructure.models.FreteExpresso;
import com.itaulatam.ms_calculodefrete.infrastructure.models.FreteNormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoDeTransporte {
    private FreteContext freteContext;
    private List<FreteStrategyInterface> freteStrategies;

    public TipoDeTransporte() {
    }

    @Autowired
    public TipoDeTransporte(FreteContext freteContext, List<FreteStrategyInterface> freteStrategies) {
        this.freteContext = freteContext;
        this.freteStrategies = freteStrategies;
    }

    public FreteStrategyInterface selecionarFreteStrategy(PedidoFreteRequestDto requestDto){
        switch (requestDto.getTipoDeTransporte()) {
            case NORMAL -> freteStrategies.add(0, new FreteNormal());
            case EXPRESSO -> freteStrategies.add(0, new FreteExpresso());
        }

        freteContext.setFreteStrategy(freteStrategies.get(0));

        return freteStrategies.get(0);
    }

}
