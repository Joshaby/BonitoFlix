package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Episodio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodioRepository extends CrudRepository<Episodio, Integer> {

}
