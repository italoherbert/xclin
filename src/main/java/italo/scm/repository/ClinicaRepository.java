package italo.scm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.Clinica;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {

	@Query("select count(*)=1 from Clinica c where lower_unaccent(c.nome)=lower_unaccent(?1)")
	public boolean existePorNome( String nome );
	
	@Query("select c from Clinica c where lower_unaccent(c.nome)=lower_unaccent(?1)")
	public Optional<Clinica> buscaPorNome( String nome );
	
	@Query("select c from Clinica c where lower_unaccent(c.nome) like lower_unaccent(?1)")
	public List<Clinica> filtra( String nomeIni );
	
}
