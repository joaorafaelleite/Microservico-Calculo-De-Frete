package com.mscalculodefrete.valorfrete.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FreteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void calcularSucessoFreteNormal() throws Exception {
        var requestDto = new PedidoFreteRequestDto();
        requestDto.setPesoDoPacote(100.00);
        requestDto.setDistanciaDaEntrega(100.00);
        requestDto.setTipoDeTransporte("NORMAL");

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valorDoFrete").exists());
    }

    @Test
    void calcularSucessoFreteExpresso() throws Exception {
        var requestDto = new PedidoFreteRequestDto();
        requestDto.setPesoDoPacote(100.00);
        requestDto.setDistanciaDaEntrega(100.00);
        requestDto.setTipoDeTransporte("EXPRESSO");

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valorDoFrete").exists());
    }

    @Test
    void calcularErroNoCalculo() throws Exception {
        var requestDto = new PedidoFreteRequestDto();
        requestDto.setPesoDoPacote(100.00);
        requestDto.setDistanciaDaEntrega(100.00);
        requestDto.setTipoDeTransporte(null);

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void calcularErroTransporteInvalido() throws Exception {
        var requestDto = new PedidoFreteRequestDto();
        requestDto.setPesoDoPacote(100.00);
        requestDto.setDistanciaDaEntrega(100.00);
        requestDto.setTipoDeTransporte("ULTRA EXPRESSO");

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }



    @Test
    void calcularErro400PayloadIncompleto() throws Exception {
        String payloadInvalido = "{}";

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadInvalido))
                .andExpect(status().isBadRequest());
    }

    @Test
    void calcularErro400FaltaTipoDeTransporte() throws Exception {
        var requestDto = new PedidoFreteRequestDto();
        requestDto.setPesoDoPacote(100.00);
        requestDto.setDistanciaDaEntrega(100.00);
        requestDto.setTipoDeTransporte(null);

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

}