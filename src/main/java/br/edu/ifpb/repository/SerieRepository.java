package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Serie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SerieRepository extends CrudRepository<Serie, Integer> {


    Optional<Serie> findByNome(String nome);
}
