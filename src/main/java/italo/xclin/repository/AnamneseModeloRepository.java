package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.AnamneseModelo;

public interface AnamneseModeloRepository extends JpaRepository<AnamneseModelo, Long> {

	@Query("select count(*)>0 "
			+ "from AnamneseModelo "
			+ "where lower_unaccent( nome ) like lower_unaccent(?1)")
	public boolean existePorNome( String nome );
		
}
