package italo.xclin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.ProfissionalExameVinculo;

public interface ProfissionalExameVinculoRepository 
		extends JpaRepository<ProfissionalExameVinculo, Long>{

	@Query("select v "
			+ "from ProfissionalExameVinculo v "
			+ "where v.profissional.id=?1 and v.exame.id=?2")
	public Optional<ProfissionalExameVinculo> busca( Long profissionalId, Long exameId );
	
}
