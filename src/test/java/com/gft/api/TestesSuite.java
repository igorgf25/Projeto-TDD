package com.gft.api;

import com.gft.api.controller.MainControllerTest;
import com.gft.api.controller.UsuarioControllerTest;
import com.gft.api.service.CaixaEletronicoServiceTest;
import com.gft.api.service.SaqueServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@SelectClasses({MainControllerTest.class, UsuarioControllerTest.class,
        CaixaEletronicoServiceTest.class, SaqueServiceTest.class})
@Suite
public class TestesSuite {
}
