package br.edu.ifpb.resource;

import br.edu.ifpb.domain.Serie;
import br.edu.ifpb.domain.Usuario;
import br.edu.ifpb.repository.SerieRepository;
import br.edu.ifpb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView cadastrarSerie(Serie serie, HttpSession session){

        ModelAndView modelSeriePage = new ModelAndView("redirect:/serie/page");

        Usuario usuarioFromSession = (Usuario) session.getAttribute("usuario");
        Usuario usuarioFromBD = usuarioRepository.findByEmail(usuarioFromSession.getEmail()).get();

        serie.setUsuario(usuarioFromBD);
//        Optional<Serie> retornoSerie =  serieRepository.findByNomeAndUsuario(serie.getNome(), serie.getUsuario());
//        if(retornoSerie.isPresent()){
//            model.addAttribute("error", "serieAlreadyExists");
//            return "page";
//        }
//        model.addAttribute("serieAdicionada", serie);
        serieRepository.save(serie);
        return modelSeriePage;
    }

    //ListarSerie
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listaSerie(HttpSession session) {
        Usuario usuarioFromSession = (Usuario) session.getAttribute("usuario");
        Usuario usuarioFromBD = usuarioRepository.findByEmail(usuarioFromSession.getEmail()).get();

        List<Serie> listaSeries = serieRepository.findAllByUsuario(usuarioFromBD);

        ModelAndView modelAndView = new ModelAndView("serie/page");

        modelAndView.addObject("listaSerie", listaSeries);
        modelAndView.addObject("usuarioNome", usuarioFromBD.getNome());

        return modelAndView;
    }


    //DeletarSerie
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deletarSerie(@PathVariable Integer id, Model model){

         Optional<Serie> serie = serieRepository.findById(id);

         if(!serie.isPresent()){
            model.addAttribute("error", "serieNoExists");
            return  "redirect:deletar";
         }
         serieRepository.delete( serie.get());
         model.addAttribute("sucesso", "deletadoComSucesso");
         return "page";
    }


}
