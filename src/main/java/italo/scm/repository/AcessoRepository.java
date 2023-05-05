package italo.scm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.Acesso;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {

	@Query("select a from Acesso a where a.grupo.id=?1 and a.recurso.id=?2")
	Optional<Acesso> busca( Long grupoId, Long recursoId );
	
}
