package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
}
