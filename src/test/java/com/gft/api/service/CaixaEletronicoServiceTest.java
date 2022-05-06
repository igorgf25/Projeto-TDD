package com.gft.api.service;

import com.gft.api.model.CaixaEletronico;
import com.gft.api.model.NotasSaque;
import com.gft.api.repository.CaixaEletronicoRepository;
import com.gft.api.service.CaixaEletronicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CaixaEletronicoServiceTest {

    @InjectMocks
    CaixaEletronicoService caixaService;

    @Mock
    CaixaEletronicoRepository caixaRepository;

    private CaixaEletronico caixaEletronico;
    private NotasSaque notasSaque;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        startObjects();
    }

    @Test
    public void caixaEletronicoTestModificaDadosCaixaEletronico() throws Exception {
        when(caixaRepository.getById(1l))
                .thenReturn(caixaEletronico);

        CaixaEletronico resposta = caixaService.realizarSaque(notasSaque, BigDecimal.valueOf(220d));

        assertEquals(9, resposta.getNotas100());
        assertEquals(8, resposta.getNotas50());
        assertEquals(9, resposta.getNotas20());
        assertEquals(10, resposta.getNotas10());
    }

    private void startObjects() {
        caixaEletronico = new CaixaEletronico(1l, 10, 10, 10, 10, BigDecimal.valueOf(1800));
        notasSaque = new NotasSaque(1, 2, 1, 0);
    }

}
