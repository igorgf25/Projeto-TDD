package com.gft.api.service;

import com.gft.api.model.CaixaEletronico;
import com.gft.api.model.NotasSaque;
import com.gft.api.model.Saque;
import com.gft.api.model.Usuario;
import com.gft.api.repository.SaqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class SaqueService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CaixaEletronicoService caixaService;

    @Autowired
    SaqueRepository saqueRepository;

    public NotasSaque realizarSaque(Saque saque) throws Exception {

        Usuario usuarioLogado = usuarioService.buscarUsuarioLogado();
        CaixaEletronico caixaEletronico = caixaService.retornaCaixa();
        NotasSaque notasSaque = new NotasSaque();

        if (usuarioLogado.getConta().getSaldo() < saque.getValor()) {
            throw new Exception("Valor do saque maior que saldo da conta.");
        } else if (caixaEletronico.getValor_total() < saque.getValor()) {
            throw new Exception("Valor do saque maior que valor contido no caixa");
        } else if (saque.getValor() < 0) {
            throw new Exception("Valor invalido por favor insira um valor valído.");
        } else if (saque.getValor() % 10 != 0) {
            throw new Exception("Não é possível sacar valores quebrados(Exemplo: 11, 23 e 55)");
        }

        //calculando notas para saque

        double resto;

        double valorSaque = saque.getValor();

        notasSaque.setNotas100((int)valorSaque/100);
        resto = valorSaque % 100;

        notasSaque.setNotas50((int)resto/50);
        resto = resto % 50;

        notasSaque.setNotas20((int)resto/20);
        resto = resto % 20;

        notasSaque.setNotas10((int)resto/10);
        resto = resto % 10;

        //Salvando dados do saque

        saque.setUsuario_id(usuarioLogado.getId());
        saque.setHorario(LocalTime.now());
        salvarSaque(saque);

        //Altera valores do caixa eletronico

        caixaService.realizarSaque(notasSaque, saque.getValor());

        //Altera o saldo da conta do usuario

        usuarioLogado.getConta().setSaldo(usuarioLogado.getConta().getSaldo() - saque.getValor());

        usuarioService.salvarUsuario(usuarioLogado);

        return notasSaque;
    }

    public Saque salvarSaque(Saque saque) {
        return saqueRepository.save(saque);
    }
}
