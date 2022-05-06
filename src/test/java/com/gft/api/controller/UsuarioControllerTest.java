package com.gft.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.api.model.Saque;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void usuarioTestSacar() throws Exception {

        Saque saque = new Saque();
        saque.setValor(BigDecimal.valueOf(200d));

        mockMvc.perform(post("/usuario/saque")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(saque)))
                .andExpect(status().isFound());
    }

    @Test
    public void usuarioTestGetPaginaSaque() throws Exception {
        mockMvc.perform(get("/usuario/saque"))
                .andExpect(status().isFound());
    }

    @Test
    public void usuarioTestGetPaginaCaixa() throws Exception {
        mockMvc.perform(get("/usuario/caixa"))
                .andExpect(status().isFound());
    }
}
