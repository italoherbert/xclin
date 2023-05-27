package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Acesso;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {

	@Query("select a from Acesso a where a.grupo.id=?1 and a.recurso.id=?2")
	public Optional<Acesso> busca( Long grupoId, Long recursoId );
		
	@Query("select a from Acesso a where a.grupo.id=?1 order by a.recurso.id desc")
	public List<Acesso> buscaPorGrupo( Long grupoId );
	
}
