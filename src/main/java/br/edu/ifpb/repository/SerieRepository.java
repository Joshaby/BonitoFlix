package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Serie;
import br.edu.ifpb.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends CrudRepository<Serie, Integer> {

    List<Serie> findAllByUsuario(Usuario usuario);
}