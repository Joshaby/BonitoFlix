package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository repository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUsuario(
            @ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes, HttpSession session) {

        ModelAndView modelError = new ModelAndView("redirect:/usuario/new");
        ModelAndView modelSerie = new ModelAndView("redirect:/serie/page");

        Optional<Usuario> usuarioFromBD = repository.findByEmail(usuario.getEmail());

        if (usuarioFromBD.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "usuarioAlreadyExists");
            return modelError;
        }

        session.setAttribute("usuario", usuario);

        repository.save(usuario);
        return modelSerie;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String getFormUsuario(
            @ModelAttribute("usuario") Usuario usuario, @ModelAttribute("error") String error, Model model) {

        model.addAttribute("usuario", usuario);
        model.addAttribute("error", error);
        return "usuario/form";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewFormUsuario(@ModelAttribute("error") String error, Model model) {

        model.addAttribute("error", error);
        return "usuario/new";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(Usuario usuario, RedirectAttributes redirectAttributes, HttpSession session) {

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

        session.setAttribute("usuario", usuarioFromBD.get());

        return modelTemporada;
    }
}
