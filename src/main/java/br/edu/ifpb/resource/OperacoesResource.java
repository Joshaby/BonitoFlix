package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class OperacoesResource {

    @Autowired
    private UsuarioRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {

        ModelAndView modelError = new ModelAndView("redirect:/usuario/form");
        ModelAndView modelTemporada = new ModelAndView("redirect:/serie/page");

        Optional<Usuario> usuarioFromBD = repository.findByEmail(usuario.getEmail());

        if (usuarioFromBD.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "invalidEmail");
            return modelError;
        }
        else {
            if (!usuarioFromBD.get().getSenha().equals(usuario.getSenha())) {
                redirectAttributes.addFlashAttribute("error", "invalidPassword");
                return modelError;
            }
        }

        redirectAttributes.addFlashAttribute("usuario", usuarioFromBD);

        return modelTemporada;
    }
}
