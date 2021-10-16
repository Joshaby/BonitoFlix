package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Temporada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporadaRepository extends CrudRepository<Temporada, Integer> {

    List<Temporada> findAll();

}
