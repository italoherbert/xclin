package italo.scm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	@Query("select count(*)=1 from Profissional p where lower_unaccent(p.nome)=lower_unaccent(?1)")
	public boolean existePorNome( String nome );
	
	@Query("select p from Profissional p where lower_unaccent(p.nome)=lower_unaccent(?1)")
	public Optional<Profissional> buscaPorNome( String nome );
	
	@Query("select p from Profissional p where lower_unaccent(p.nome) like lower_unaccent(?1)")
	public List<Profissional> filtraPorNome( String nomeIni );
		
	@Query("select p from Profissional p "
			+ "join ProfissionalClinicaVinculo v "
		 + "where lower_unaccent(v.clinica.nome) like lower_unaccent(?1)")
	public List<Profissional> filtraPorClinica( String clinicaNomeIni );
	
	@Query("select p from Profissional p "
			+ "join ProfissionalClinicaVinculo v "
		 + "where "
		 	+ "lower_unaccent(p.nome) like lower_unaccent(?1) and "
		 	+ "lower_unaccent(v.clinica.nome) like lower_unaccent(?2)") 
	public List<Profissional> filtra( String nomeIni, String clinicaNomeIni );
	
}