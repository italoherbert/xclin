package italo.xclin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.xclin.model.PacienteAnexo;

public interface PacienteAnexoRepository extends JpaRepository<PacienteAnexo, Long> {

	public List<PacienteAnexo> findByPacienteId( Long pacienteId );
	
}
