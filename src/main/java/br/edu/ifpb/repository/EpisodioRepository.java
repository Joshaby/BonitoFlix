package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Episodio;
import br.edu.ifpb.domain.Temporada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpisodioRepository extends CrudRepository<Episodio, Integer> {

    List<Episodio> findAllByTemporada(Temporada temporada);

    Optional<Episodio> findByNomeAndTemporada(String nome, Temporada temporada);
}
