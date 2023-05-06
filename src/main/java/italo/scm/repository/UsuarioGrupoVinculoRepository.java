package italo.scm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.UsuarioGrupoVinculo;

public interface UsuarioGrupoVinculoRepository extends JpaRepository<UsuarioGrupoVinculo, Long> {

	@Query("select v from UsuarioGrupoVinculo v where v.usuario.id=?1 and v.grupo.id=?2")
	Optional<UsuarioGrupoVinculo> busca( Long usuarioId, Long grupoId );
	
	void deleteByUsuarioId( Long usuarioId );
	
}
