package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Serie;
import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.repository.SerieRepository;
import br.edu.ifpb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/serie")
public class SerieResource {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //CadastrarSerie
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView cadastrarSerie(Serie serie, HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("usuario") == null) {
            ModelAndView modelIndex = new ModelAndView("redirect:/");
            return modelIndex;
        }

        ModelAndView modelSeriePage = new ModelAndView("redirect:/serie/page");

        Usuario usuarioFromSession = (Usuario) session.getAttribute("usuario");
        Usuario usuarioFromBD = usuarioRepository.findByEmail(usuarioFromSession.getEmail()).get();

        serie.setUsuario(usuarioFromBD);

        redirectAttributes.addFlashAttribute("serieNome", serie.getNome());

        Optional<Serie> retornoSerie =  serieRepository.findByNomeAndUsuario(serie.getNome(), usuarioFromBD);
        if(retornoSerie.isPresent()){
            redirectAttributes.addFlashAttribute("alert", "add-error");
            return modelSeriePage;
        }

        redirectAttributes.addFlashAttribute("alert", "add");

        serieRepository.save(serie);
        return modelSeriePage;
    }

    //ListarSerie
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listaSerie(
            @ModelAttribute("alert") String alert, @ModelAttribute("serieNome") String serieNome, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            ModelAndView modelIndex = new ModelAndView("redirect:/");
            return modelIndex;
        }

        Usuario usuarioFromSession = (Usuario) session.getAttribute("usuario");
        Usuario usuarioFromBD = usuarioRepository.findByEmail(usuarioFromSession.getEmail()).get();

        List<Serie> listaSeries = serieRepository.findAllByUsuario(usuarioFromBD);

        ModelAndView modelAndView = new ModelAndView("serie/page");

        modelAndView.addObject("listaSerie", listaSeries);
        modelAndView.addObject("alert", alert);
        modelAndView.addObject("serieNome", serieNome);

        return modelAndView;
    }

    //DeletarSerie
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletarSerie(
            @PathVariable Integer id, HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("usuario") == null) {
            ModelAndView modelIndex = new ModelAndView("redirect:/");
            return modelIndex;
        }

        ModelAndView modelSeriePage = new ModelAndView("redirect:/serie/page");

        Optional<Serie> serie = serieRepository.findById(id);

        serieRepository.delete(serie.get());

        redirectAttributes.addFlashAttribute("alert", "del");
        redirectAttributes.addFlashAttribute("serieNome", serie.get().getNome());

        return modelSeriePage;
    }

    @RequestMapping(value = "/{id}/temporada", method = RequestMethod.GET)
    public ModelAndView temporadas(@PathVariable Integer id, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            ModelAndView modelIndex = new ModelAndView("redirect:/");
            return modelIndex;
        }

        ModelAndView modelTemporadas = new ModelAndView("redirect:/temporada/page");

        Optional<Serie> serieFromBD = serieRepository.findById(id);

        session.setAttribute("serie", serieFromBD.get());

        return modelTemporadas;
    }

}
