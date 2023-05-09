package italo.scm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.Diretor;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {

	@Query("select count(*)=1 from Diretor d where lower_unaccent(d.nome)=lower_unaccent(?1)")
	public boolean existePorNome( String nome );
	
	@Query("select d from Diretor d where lower_unaccent(d.nome)=lower_unaccent(?1)")
	public Optional<Diretor> buscaPorNome( String nome );
	
	@Query("select d from Diretor d where lower_unaccent(d.nome) like lower_unaccent(?1)")
	public List<Diretor> filtraPorNome( String nomeIni );
		
	@Query("select d from Diretor d "
			+ "join DiretorClinicaVinculo v "
		 + "where lower_unaccent(v.clinica.nome) like lower_unaccent(?1)")
	public List<Diretor> filtraPorClinica( String clinicaNomeIni );
	
	@Query("select d from Diretor d "
			+ "join DiretorClinicaVinculo v "
		 + "where "
		 	+ "lower_unaccent(d.nome) like lower_unaccent(?1) and "
		 	+ "lower_unaccent(v.clinica.nome) like lower_unaccent(?2)") 
	public List<Diretor> filtra( String nomeIni, String clinicaNomeIni );
	
}
