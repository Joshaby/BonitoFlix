package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Temporada;
import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView cadastrarTemporada(Temporada temporada){

        ModelAndView modelAndView = new ModelAndView("redirect:/temporada/page");
        //pegar sessão para colocar usuários no objeto.
        temporadaRepository.save(temporada);
        return modelAndView;
    }

    //ListarTemporada
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listarTemporada(@ModelAttribute("usuario") Usuario usuario){

        ModelAndView modelAndView = new ModelAndView("temporada/page");
        List<Temporada> listaTemporadas =  temporadaRepository.findAll();
        modelAndView.addObject("listaTemporada", listaTemporadas);
        modelAndView.addObject("usuario", usuario);
        System.out.println("\n\n\n entrou aqui" +
                "" +
                "" +
                "" +
                "" +
                "" +
                "entrou");
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
