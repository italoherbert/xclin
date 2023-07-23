package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Recepcionista;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long> {

	@Query("select count(*)=1 "
			+ "from Recepcionista r "
			+ "where r.clinica.id=?1 and lower_unaccent(r.nome)=lower_unaccent(?2)")
	public boolean existeNaClinicaPorNome( Long clinicaId, String nome );
		
	@Query("select r from Recepcionista r where lower(r.nome) like lower(?1)")
	public List<Recepcionista> filtra( String nomeIni );
	
	@Query("select r from Recepcionista r where r.usuario.id=?1")
	public Optional<Recepcionista> buscaPorUsuario( Long uid );
	
	@Query("select r "
		 + "from Recepcionista r "
		 + "where "
		 	+ "r.clinica.id=?1 and "
		 	+ "lower_unaccent(r.nome) like lower_unaccent(?1)")
	public List<Recepcionista> filtra( Long clinicaId, String filtroNome );
	
	@Query("select r "
			 + "from Recepcionista r "
			 + "where "
			 	+ "r.clinica.id=?1")
	public List<Recepcionista> listaPorClinica( Long clinicaId );
		
	@Query("select r "
		 + "from Recepcionista r "
		 + "where r.id=?1 and r.clinica.id in (?2)")
	public Optional<Recepcionista> busca( Long recepcionistaId, Long[] clinicasIDs );
		
	
}
