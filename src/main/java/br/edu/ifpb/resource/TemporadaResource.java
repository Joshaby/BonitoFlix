package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Serie;
import br.edu.ifpb.domain.Temporada;
import br.edu.ifpb.repository.TemporadaRepository;
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
@RequestMapping(value = "/temporada")
public class TemporadaResource {

    @Autowired
    private TemporadaRepository temporadaRepository;

    //CadastrarTemporada
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView cadastrarTemporada(
            Temporada temporada, HttpSession session, RedirectAttributes redirectAttributes){

        ModelAndView modelAndView = new ModelAndView("redirect:/temporada/page");

        redirectAttributes.addFlashAttribute("temporadaNome", temporada.getNome());

        Serie serieFromSession = (Serie) session.getAttribute("serie");

        temporada.setSerie(serieFromSession);

        Optional<Temporada> temporadaFromBD = temporadaRepository.findByNomeAndSerie(temporada.getNome(), serieFromSession);
        if (temporadaFromBD.isPresent()) {
            redirectAttributes.addFlashAttribute("alert", "add-error");
            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("alert", "add");

        temporadaRepository.save(temporada);
        return modelAndView;
    }

    //ListarTemporada
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listarTemporada(
            @ModelAttribute("alert") String alert, @ModelAttribute("temporadaNome") String temporadaNome, HttpSession session) {

        Serie serie = (Serie) session.getAttribute("serie");

        List<Temporada> listaTemporada =  temporadaRepository.findAllBySerie(serie);

        ModelAndView modelAndView = new ModelAndView("temporada/page");
        modelAndView.addObject("listaTemporada", listaTemporada);
        modelAndView.addObject("alert", alert);
        modelAndView.addObject("temporadaNome", temporadaNome);

        return modelAndView;
    }

    //DeletarTemporada
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletarTemporada(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("redirect:/temporada/page");

        Optional<Temporada> temporada = temporadaRepository.findById(id);

        temporadaRepository.delete(temporada.get());

        redirectAttributes.addFlashAttribute("alert", "del");
        redirectAttributes.addFlashAttribute("temporadaNome", temporada.get().getNome());

        return modelAndView;
    }

    @RequestMapping(value = "/{id}/episodio", method = RequestMethod.GET)
    public ModelAndView episodio(@PathVariable Integer id, HttpSession session) {

        ModelAndView modelEpisodios = new ModelAndView("redirect:/episodio/page");

        Temporada temporada = temporadaRepository.findById(id).get();

        session.setAttribute("temporada", temporada);

        return modelEpisodios;
    }

}
