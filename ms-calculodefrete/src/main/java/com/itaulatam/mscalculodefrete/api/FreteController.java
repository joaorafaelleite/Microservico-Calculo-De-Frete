package com.itaulatam.mscalculodefrete.api;

import com.itaulatam.mscalculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.mscalculodefrete.api.response.PedidoDeFreteResponseDto;
import com.itaulatam.mscalculodefrete.business.FreteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frete")
public class FreteController
{
    private FreteService freteService;

    @Autowired
    public FreteController(FreteService freteService) {
        this.freteService = freteService;
    }

    @PostMapping("/calcular")
    public PedidoDeFreteResponseDto calcular(@Valid @RequestBody PedidoFreteRequestDto requestDto) throws Exception {
        return  freteService.calcular(requestDto);
    }
}
