package com.itaulatam.ms_calculodefrete.api;

import com.itaulatam.ms_calculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.ms_calculodefrete.api.response.PedidoDeFreteResponseDto;
import com.itaulatam.ms_calculodefrete.business.FreteService;
import com.itaulatam.ms_calculodefrete.infrastructure.interfaces.FreteStrategyInterface;
import com.itaulatam.ms_calculodefrete.infrastructure.models.FreteContext;
import com.itaulatam.ms_calculodefrete.infrastructure.models.FreteExpresso;
import com.itaulatam.ms_calculodefrete.infrastructure.models.FreteNormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/frete")
public class FreteController
{
    private FreteContext freteContext;
    private List<FreteStrategyInterface> freteStrategies;
    private FreteService freteService;

    @Autowired
    public FreteController(FreteContext freteContext, List<FreteStrategyInterface> freteStrategies, FreteService freteService) {
        this.freteContext = freteContext;
        this.freteStrategies = freteStrategies;
        this.freteService = freteService;
    }

    @PostMapping("/calcular")
    public PedidoDeFreteResponseDto calcular(@RequestBody @Validated PedidoFreteRequestDto requestDto){
        switch (requestDto.getTipoDeTransporte()) {
            case NORMAL -> freteStrategies.add(0, new FreteNormal());
            case EXPRESSO -> freteStrategies.add(0, new FreteExpresso());
        }

        freteContext.setFreteStrategy(freteStrategies.get(0));

        return  freteService.calcular(requestDto, freteStrategies.get(0));
    }
}
