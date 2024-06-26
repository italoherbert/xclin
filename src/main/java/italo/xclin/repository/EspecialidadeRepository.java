package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

	@Query("select count(*)=1 "
			+ "from Especialidade e "
			+ "where e.clinica.id=?1 and lower(e.nome)=lower(?2)")
	public boolean existePorNome( Long clinicaId, String nome );
	
	@Query("select e "
			+ "from Especialidade e "
			+ "where e.clinica.id=?1 and lower(e.nome)=lower(?2)")
	public Optional<Especialidade> buscaPorNome( Long clinicaId, String nome );
	
	@Query("select e "
			+ "from Especialidade e "
			+ "where e.clinica.id=?1 and lower(e.nome) like lower(?2)")
	public List<Especialidade> filtra( Long clinicaId, String nomeIni );
	
	@Query("select e "
			+ "from Especialidade e "
			+ "where e.clinica.id=?1")
	public List<Especialidade> listaPorClinica( Long clinicaId );
		
	@Query("select e "
			+ "from Especialidade e "
			+ "where e.clinica.id in (?1)")
	public List<Especialidade> listaPorClinicasIDs( Long[] clinicasIDs );
	
}
