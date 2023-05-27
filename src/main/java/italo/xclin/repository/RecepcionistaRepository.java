package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Recepcionista;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long> {

	@Query("select count(*)=1 from Recepcionista r where lower(r.nome)=lower(?1)")
	public boolean existePorNome( String nome );
	
	@Query("select r from Recepcionista r where lower(r.nome)=lower(?1)")
	public Optional<Recepcionista> buscaPorNome( String nome );
	
	@Query("select r from Recepcionista r where lower(r.nome) like lower(?1)")
	public List<Recepcionista> filtra( String nomeIni );
	
	@Query("select r from Recepcionista r where r.usuario.id=?1")
	public Optional<Recepcionista> buscaPorUsuario( Long uid );
	
	@Query("select r from Recepcionista r "
			+ "join Clinica c "
		 + "where lower_unaccent(c.nome) like lower_unaccent(?1)")
	public List<Recepcionista> filtraPorClinica( String clinicaNomeIni );
	
	@Query("select r from Recepcionista r "
			+ "join Clinica c "
		 + "where "
		 	+ "lower_unaccent(r.nome) like lower_unaccent(?1) and "
		 	+ "lower_unaccent(c.nome) like lower_unaccent(?2)") 
	public List<Recepcionista> filtra( String nomeIni, String clinicaNomeIni );
	
	@Query("select r "
		 + "from Recepcionista r "
		 + "where "
		 	+ "r.clinica.id=?1 and "
		 	+ "lower_unaccent(r.nome) like lower_unaccent(?1)")
	public List<Recepcionista> filtra( Long clinicaId, String nomeIni );
	
	@Query("select r "
			 + "from Recepcionista r "
			 + "where "
			 	+ "r.clinica.id=?1")
	public List<Recepcionista> filtra( Long clinicaId );
		
	@Query("select r "
		 + "from Recepcionista r "
		 + "where r.id=?1 and r.clinica.id in (?2)")
	public Optional<Recepcionista> busca( Long recepcionistaId, Long[] clinicasIDs );
		
	
}
