package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.AnamneseModelo;

public interface AnamneseModeloRepository extends JpaRepository<AnamneseModelo, Long> {

	@Query("select count(*)>0 "
			+ "from AnamneseModelo m "
			+ "where m.profissional.id=?1 and "
				+ "lower_unaccent( m.nome ) like lower_unaccent(?2)")
	public boolean existePorNome( Long profissionalId, String nome );		
		
}
