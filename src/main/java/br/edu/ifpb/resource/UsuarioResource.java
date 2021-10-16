package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository repository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUsuario(Usuario usuario, Model model) {

        Optional<Usuario> usuarioFromBD = repository.findByEmail(usuario.getEmail());
        if (usuarioFromBD.isPresent()) {
            model.addAttribute("error", "usuarioAlreadyExists");
            return "usuario/new";
        }

        repository.save(usuario);
        return "redirect:/";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String getFormUsuario() {
        return "usuario/form";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewFormUsuario() {
        return "usuario/new";
    }
}
