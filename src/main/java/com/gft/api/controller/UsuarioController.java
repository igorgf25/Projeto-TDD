package com.gft.api.controller;

import com.gft.api.model.NotasSaque;
import com.gft.api.model.Saque;
import com.gft.api.service.SaqueService;
import com.gft.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    SaqueService saqueService;

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("saque")
    public ModelAndView paginaSaque() {
        ModelAndView mv = new ModelAndView("usuario/saque.html");
        mv.addObject("saque", new Saque());
        mv.addObject("conta", usuarioService.buscarUsuarioLogado().getConta());
        System.out.println(usuarioService.buscarUsuarioLogado().getSaques());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "saque")
    public ModelAndView saque(Saque saque, RedirectAttributes redirectAttributes) {
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
}
