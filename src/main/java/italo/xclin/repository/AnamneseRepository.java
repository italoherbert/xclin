package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Anamnese;

public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {

	@Query("select count(*)=1 "
			+ "from Anamnese a "
			+ "where a.paciente.id=?1" )	
	public boolean existeAnamnesePorPacienteId( Long pacienteId );
	
}
