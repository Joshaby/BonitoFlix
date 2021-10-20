package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Episodio;
import br.edu.ifpb.domain.Temporada;
import br.edu.ifpb.repository.EpisodioRepository;
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
@RequestMapping(value = "/episodio")
public class EpisodioResource {

    @Autowired
    private EpisodioRepository episodioRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addEpisodios(Episodio episodio, HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("usuario") == null) {
            ModelAndView modelIndex = new ModelAndView("redirect:/");
            return modelIndex;
        }

        ModelAndView modelEpisodioPage = new ModelAndView("redirect:/episodio/page");

        Temporada temporada = (Temporada) session.getAttribute("temporada");

        episodio.setTemporada(temporada);

        redirectAttributes.addFlashAttribute("episodioNome", episodio.getNome());

        Optional<Episodio> episodioFromBD = episodioRepository.findByNomeAndTemporada(episodio.getNome(), temporada);
        if (episodioFromBD.isPresent()) {
            redirectAttributes.addFlashAttribute("alert", "add-error");
            return modelEpisodioPage;
        }

        redirectAttributes.addFlashAttribute("alert", "add");

        episodioRepository.save(episodio);
        return modelEpisodioPage;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView getEpisodios(
            @ModelAttribute("alert") String alert, @ModelAttribute("episodioNome") String episodioNome, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            ModelAndView modelIndex = new ModelAndView("redirect:/");
            return modelIndex;
        }

        Temporada temporada = (Temporada) session.getAttribute("temporada");

        List<Episodio> listaEpisodio = episodioRepository.findAllByTemporada(temporada);

        ModelAndView modelEpisodioPage = new ModelAndView("episodio/page");
        modelEpisodioPage.addObject("listaEpisodio", listaEpisodio);
        modelEpisodioPage.addObject("alert", alert);
        modelEpisodioPage.addObject("episodioNome", episodioNome);

        return modelEpisodioPage;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEpisodio(
            @PathVariable Integer id, RedirectAttributes redirectAttributes, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            ModelAndView modelIndex = new ModelAndView("redirect:/");
            return modelIndex;
        }

        ModelAndView modelEpisodioPage = new ModelAndView("redirect:/episodio/page");

        Optional<Episodio> episodioFromBD = episodioRepository.findById(id);

        episodioRepository.delete(episodioFromBD.get());

        redirectAttributes.addFlashAttribute("alert", "del");
        redirectAttributes.addFlashAttribute("episodioNome", episodioFromBD.get().getNome());

        return modelEpisodioPage;
    }

    @RequestMapping(value = "/assistido/{id}", method = RequestMethod.GET)
    public ModelAndView assistidoEpisodio(@PathVariable Integer id, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            ModelAndView modelIndex = new ModelAndView("redirect:/");
            return modelIndex;
        }

        ModelAndView modelEpisodioPage = new ModelAndView("redirect:/episodio/page");

        Episodio episodioFromBD = episodioRepository.findById(id).get();

        episodioFromBD.setAssistidoCheck(!episodioFromBD.getAssistidoCheck());

        episodioRepository.save(episodioFromBD);

        return modelEpisodioPage;
    }
}
