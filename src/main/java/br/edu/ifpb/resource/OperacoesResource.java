package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OperacoesResource {

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Usuario usuario) {
        return "usuario/new";
    }

    @RequestMapping(value = "/loginform", method = RequestMethod.GET)
    public String loginForm(Usuario usuario) {
        return "usuario/form";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Usuario usuario) {
        return "";
    }
}
