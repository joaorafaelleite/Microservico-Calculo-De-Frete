package com.mscalculodefrete.valorfrete.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscalculodefrete.valorfrete.api.request.PedidoFreteRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FreteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCalcularFreteNormalComSucesso() throws Exception {
        var requestDto = new PedidoFreteRequestDto(100.00, 100.00, "NORMAL");

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valorDoFrete").exists());
    }

    @Test
    void deveCalcularFreteExpressoComSucesso() throws Exception {
        var requestDto = new PedidoFreteRequestDto(100.00, 100.00, "EXPRESSO");

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valorDoFrete").exists());
    }

    @Test
    void deveRetornarBadRequestParaTransporteInvalido() throws Exception {
        var requestDto = new PedidoFreteRequestDto(100.00, 100.00, "ULTRA EXPRESSO");

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarBadRequestParaPayloadIncompleto() throws Exception {
        String payloadInvalido = "{}";

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadInvalido))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarBadRequestParaFaltaTipoDeTransporte() throws Exception {
        var requestDto = new PedidoFreteRequestDto(100.00, 100.00, null);

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarBadRequestParaPesoNegativo() throws Exception {
        var requestDto = new PedidoFreteRequestDto(-1.0, 100.00, "NORMAL");

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarBadRequestParaDistanciaNegativa() throws Exception {
        var requestDto = new PedidoFreteRequestDto(100.00, -1.0, "NORMAL");

        mockMvc.perform(post("/frete/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }
}