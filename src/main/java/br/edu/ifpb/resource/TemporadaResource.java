package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Temporada;
import br.edu.ifpb.repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/temporada")
public class TemporadaResource {

    @Autowired
    private TemporadaRepository temporadaRepository;


    //CadastrarTemporada
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String cadastrarTemporada(Temporada temporada, Model model){

        model.addAttribute("temporadaAdicionada", temporada);
        temporadaRepository.save(temporada);
        return "temporada/page"; //verificar questão do modal com zé
    }

    //ListarTemporada
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listarTemporada(){

        ModelAndView modelAndView = new ModelAndView("temporada/page");
        List<Temporada> listaTemporadas =  temporadaRepository.findAll();
        modelAndView.addObject("listaTemporada", listaTemporadas);

        return modelAndView;
    }


    //DeletarTemporada
    @RequestMapping(value = "/deletar", method = RequestMethod.DELETE)
    public String deletarTemporada(Integer id, Model model){

        Optional<Temporada> temporada = temporadaRepository.findById(id);

        if(!temporada.isPresent()){
            model.addAttribute("error", "temporadaNoExists");
            return  "redirect:temporada/page";
        }
        temporadaRepository.delete(temporada.get());
        model.addAttribute("sucesso", "deletadaComSucesso");
        return "temporada/page";
    }

}
