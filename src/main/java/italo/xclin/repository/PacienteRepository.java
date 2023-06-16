package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	@Query("select count(*)=1 "
			+ "from Paciente p "
			+ "where lower_unaccent(p.nome)=lower_unaccent(?1) and p.clinica.id=?2")
	public boolean existePorNome( String nome, Long clinicaId );
	
	@Query("select p "
			+ "from Paciente p "
			+ "where lower_unaccent(p.nome)=lower_unaccent(?1) and p.clinica.id=?2")
	public Optional<Paciente> buscaPorNome( String nome, Long clinicaId );
		
	@Query("select p "
			+ "from Paciente p "
			+ "where p.id=?1 and p.clinica.id in (?2)")
	public Optional<Paciente> busca( Long pacienteId, Long[] clinicasIDs );
	
	@Query("select p "
			+ "from Paciente p "
			+ "where lower_unaccent(p.nome) like lower_unaccent(?1) and p.clinica.id=?2")
	public List<Paciente> filtra( String nomeIni, Long clinicaId );
	
	@Query("select p "
			+ "from Paciente p "
			+ "where lower_unaccent(p.nome) like lower_unaccent(?1) and p.clinica.id=?2")
	public List<Paciente> lista( String nomeIni, Long clinicaId, Pageable pageable );
	
	@Query("select p "
			+ "from Paciente p "
			+ "where p.clinica.id=?1")
	public List<Paciente> filtraTodosDaClinica( Long clinicaId );
	
}
