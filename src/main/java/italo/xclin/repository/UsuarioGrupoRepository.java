package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.UsuarioGrupo;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, Long> {

	@Query("select count(*)=1 from UsuarioGrupo g where lower(nome)=lower(?1)")
	boolean existePorNome( String nome );
	
	@Query("select g from UsuarioGrupo g where lower(nome)=lower(?1)")
	Optional<UsuarioGrupo> buscaPorNome( String nome );
	
	@Query("select g from UsuarioGrupo g where lower(nome) like lower(?1)")
	List<UsuarioGrupo> filtraPorNomeIni( String nomeIni );
	
}
