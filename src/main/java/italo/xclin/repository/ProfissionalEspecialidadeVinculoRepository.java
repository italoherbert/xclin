package italo.xclin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.ProfissionalEspecialidadeVinculo;

public interface ProfissionalEspecialidadeVinculoRepository extends JpaRepository<ProfissionalEspecialidadeVinculo, Long> {	

	@Query("select v "
			+ "from ProfissionalEspecialidadeVinculo v "
			+ "where v.profissional.id=?1 and v.especialidade.id=?2")
	public Optional<ProfissionalEspecialidadeVinculo> busca( Long profissionalId, Long especialidadeId );
	
}

