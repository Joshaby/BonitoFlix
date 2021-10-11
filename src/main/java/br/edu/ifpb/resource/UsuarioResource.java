package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository repository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUsuario(@Valid Usuario usuario, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "form";
        }
        repository.save(usuario);
        return "redirect:/";
    }
}
