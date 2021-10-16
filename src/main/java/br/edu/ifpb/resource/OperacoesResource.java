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
public class OperacoesResource {

    @Autowired
    private UsuarioRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Usuario usuario, Model model) {

        Optional<Usuario> usuarioFromBD = repository.findByEmail(usuario.getEmail());
        if (usuarioFromBD.isEmpty()) {
            model.addAttribute("error", "invalidEmail");
            return "usuario/form";
        }
        else {
            if (!usuarioFromBD.get().getSenha().equals(usuario.getSenha())) {
                model.addAttribute("error", "invalidPassword");
                return "usuario/form";
            }
        }

        return "";
    }
}
