package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Serie;
import br.edu.ifpb.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/serie")
public class SerieResource {

    @Autowired
    private SerieRepository serieRepository;

    //CadastrarSerie
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String cadastrarSerie(@Valid Serie serie, Model model){
        Optional<Serie> serieR =  serieRepository.findByNome(serie.getNome());
        if(serieR.isPresent()){
            model.addAttribute("error", "serieAlreadyExists");
            return "redirect:/";
        }
        model.addAttribute("serieAdicionada", serie);
        serieRepository.save(serie);
        return "redirect:/";
    }

    //ListarSerie
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listaSerie(){
        List<Serie> listaSeries =  serieRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("serie/page");
        modelAndView.addObject("listaSerie", listaSeries);
        return modelAndView;
    }

}
