package com.gft.api.controller;

import com.gft.api.model.NotasSaque;
import com.gft.api.model.Saque;
import com.gft.api.service.CaixaEletronicoService;
import com.gft.api.service.SaqueService;
import com.gft.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    SaqueService saqueService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CaixaEletronicoService caixaService;

    @RequestMapping("saque")
    public ModelAndView paginaSaque() {
        ModelAndView mv = new ModelAndView("usuario/saque.html");
        mv.addObject("saque", new Saque());
        mv.addObject("conta", usuarioService.buscarUsuarioLogado().getConta());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "saque")
    public ModelAndView saque( Saque saque, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/usuario/saque");

        try {
            NotasSaque resposta = saqueService.realizarSaque(saque);
            redirectAttributes.addFlashAttribute("notasSaque", resposta);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao realizar saque: "
                    + e.getMessage());
        }

        return mv;
    }

    @RequestMapping("caixa")
    public ModelAndView caixa() {
        ModelAndView mv = new ModelAndView("admin/notasCaixa.html");

        mv.addObject("caixa", caixaService.retornaCaixa());

        return mv;
    }
}
