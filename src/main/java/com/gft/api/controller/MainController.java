package com.gft.api.controller;

import com.gft.api.model.Saque;
import com.gft.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index.html");
        mv.addObject("usuario", usuarioService.buscarUsuarioLogado());
        return mv;
    }

    @RequestMapping("login")
    public ModelAndView login(@RequestParam(required = false) boolean erro) {
        ModelAndView mv = new ModelAndView("usuario/login.html");
        return mv;
    }
}
