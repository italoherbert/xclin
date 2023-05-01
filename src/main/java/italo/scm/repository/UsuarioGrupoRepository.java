package italo.scm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.UsuarioGrupo;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, Long> {

	@Query("select g from UsuarioGrupo g where lower(nome)=lower(?1)")
	Optional<UsuarioGrupo> buscaPorNome( String nome );
	
}
