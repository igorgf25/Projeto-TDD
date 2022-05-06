package com.gft.api.service;

import com.gft.api.model.*;
import com.gft.api.repository.SaqueRepository;
import com.gft.api.service.CaixaEletronicoService;
import com.gft.api.service.SaqueService;
import com.gft.api.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SaqueServiceTest {

    @InjectMocks
    SaqueService saqueService;

    @Mock
    UsuarioService usuarioService;

    @Mock
    CaixaEletronicoService caixaService;

    @Mock
    SaqueRepository saqueRepository;

    private Usuario usuario;
    private CaixaEletronico caixaEletronico;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        startObjects();

    }

    @Test
    public void saqueServiceTestProcessaSaqueCorreto() {
        when(caixaService.retornaCaixa())
                .thenReturn(caixaEletronico);

        when(usuarioService.buscarUsuarioLogado())
                .thenReturn(usuario);

        when(saqueRepository.save(new Saque()))
                .thenReturn(new Saque());

        Saque saque = new Saque(BigDecimal.valueOf(110.00));
        NotasSaque notas;
        try {
            notas = saqueService.realizarSaque(saque);
        } catch (Exception e) {
            notas = null;
        }
        NotasSaque notasEsperadas = new NotasSaque(1, 0, 0, 1);

        assertEquals(notasEsperadas.getNotas10(), notas.getNotas10());
        assertEquals(notasEsperadas.getNotas20(), notas.getNotas20());
        assertEquals(notasEsperadas.getNotas50(), notas.getNotas50());
        assertEquals(notasEsperadas.getNotas100(), notas.getNotas100());
    }

    @Test
    public void saqueServiceTestRetornaErroValorAcimaDoSaldo() {
        when(caixaService.retornaCaixa())
                .thenReturn(caixaEletronico);

        when(usuarioService.buscarUsuarioLogado())
                .thenReturn(usuario);

        when(saqueRepository.save(new Saque()))
                .thenReturn(new Saque());

        Saque saque = new Saque(BigDecimal.valueOf(1700d));
        NotasSaque notas;
        try {
            notas = saqueService.realizarSaque(saque);
        } catch (Exception e) {
            assertEquals(Exception.class, e.getClass());
            assertEquals("Valor do saque maior que saldo da conta.", e.getMessage());
        }
    }

    @Test
    public void saqueServiceTestRetornaErroValorInvalido() {
        when(caixaService.retornaCaixa())
                .thenReturn(caixaEletronico);

        when(usuarioService.buscarUsuarioLogado())
                .thenReturn(usuario);

        when(saqueRepository.save(new Saque()))
                .thenReturn(new Saque());

        Saque saque = new Saque(BigDecimal.valueOf(-1500d));
        NotasSaque notas;
        try {
            notas = saqueService.realizarSaque(saque);
        } catch (Exception e) {
            assertEquals(Exception.class, e.getClass());
            assertEquals("Valor invalido por favor insira um valor valído.", e.getMessage());
        }
    }

    @Test
    public void saqueServiceTestRetornaErroValorMaiorQueCaixa() {
        when(caixaService.retornaCaixa())
                .thenReturn(new CaixaEletronico(1l, 10, 10, 10, 10, BigDecimal.valueOf(1000)));

        when(usuarioService.buscarUsuarioLogado())
                .thenReturn(usuario);

        when(saqueRepository.save(new Saque()))
                .thenReturn(new Saque());

        Saque saque = new Saque(BigDecimal.valueOf(1500d));
        NotasSaque notas;
        try {
            notas = saqueService.realizarSaque(saque);
        } catch (Exception e) {
            assertEquals(Exception.class, e.getClass());
            assertEquals("Valor do saque maior que valor contido no caixa", e.getMessage());
        }
    }

    @Test
    public void saqueServiceTestRetornaErroValorQuebrado() {
        when(caixaService.retornaCaixa())
                .thenReturn(caixaEletronico);

        when(usuarioService.buscarUsuarioLogado())
                .thenReturn(usuario);

        when(saqueRepository.save(new Saque()))
                .thenReturn(new Saque());

        Saque saque = new Saque(BigDecimal.valueOf(1005d));
        NotasSaque notas;
        try {
            notas = saqueService.realizarSaque(saque);
        } catch (Exception e) {
            assertEquals(Exception.class, e.getClass());
            assertEquals("Não é possível sacar valores quebrados(Exemplo: 11, 23 e 55)", e.getMessage());
        }
    }

    private void startObjects() {
        caixaEletronico = new CaixaEletronico(1l, 10, 10, 10, 10, BigDecimal.valueOf(1800));

        usuario = new Usuario(1l, "igor", "User", "Teste", List.of(new Role(1l, "ROLE_USER")), new Conta(1l, BigDecimal.valueOf(1600d), new Usuario()), new ArrayList<>());
    }
}
