package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Serie;
import br.edu.ifpb.domain.Temporada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemporadaRepository extends CrudRepository<Temporada, Integer> {

    List<Temporada> findAllBySerie(Serie serie);

    Optional<Temporada> findByNomeAndSerie(String nome, Serie serie);

}
