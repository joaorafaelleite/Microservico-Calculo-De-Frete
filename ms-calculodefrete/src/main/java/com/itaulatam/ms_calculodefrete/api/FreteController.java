package com.itaulatam.ms_calculodefrete.api;

import com.itaulatam.ms_calculodefrete.api.request.PedidoFreteRequestDto;
import com.itaulatam.ms_calculodefrete.api.response.PedidoDeFreteResponseDto;
import com.itaulatam.ms_calculodefrete.business.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public FreteController(FreteService freteService){
        this.freteService =  freteService;
    }
    @PostMapping("/calcular")
    public PedidoDeFreteResponseDto calcular(@RequestBody @Validated PedidoFreteRequestDto requestDto){
        return  freteService.calcular(requestDto);
    }
}
