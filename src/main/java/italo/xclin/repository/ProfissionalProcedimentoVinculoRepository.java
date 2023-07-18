package italo.xclin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.ProfissionalProcedimentoVinculo;

public interface ProfissionalProcedimentoVinculoRepository 
				extends JpaRepository<ProfissionalProcedimentoVinculo, Long> {
	
	@Query("select v "
			+ "from ProfissionalProcedimentoVinculo v "
			+ "where v.profissional.id=?1 and v.procedimento.id=?2")
	public Optional<ProfissionalProcedimentoVinculo> busca( Long profissionalId, Long procedimentoId );
	

}
