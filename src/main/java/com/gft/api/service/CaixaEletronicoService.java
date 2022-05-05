package com.gft.api.service;

import com.gft.api.model.CaixaEletronico;
import com.gft.api.model.NotasSaque;
import com.gft.api.repository.CaixaEletronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaixaEletronicoService {

    @Autowired
    CaixaEletronicoRepository caixaEletronicoRepository;

    public CaixaEletronico retornaCaixa() {
        return caixaEletronicoRepository.getById(1l);
    }

    public CaixaEletronico realizarSaque(NotasSaque notas, Double valor) throws Exception {
        CaixaEletronico caixaEletronico = retornaCaixa();

        caixaEletronico.setNotas100(caixaEletronico.getNotas100() - notas.getNotas100());
        caixaEletronico.setNotas50(caixaEletronico.getNotas50() - notas.getNotas50());
        caixaEletronico.setNotas20(caixaEletronico.getNotas20() - notas.getNotas20());
        caixaEletronico.setNotas10(caixaEletronico.getNotas10() - notas.getNotas10());
        caixaEletronico.setValor_total(caixaEletronico.getValor_total() - valor);

        caixaEletronicoRepository.save(caixaEletronico);

        return caixaEletronico;
    }
}
