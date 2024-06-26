package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	@Query("select count(*)=1 from Profissional p where lower_unaccent(p.nome)=lower_unaccent(?1)")
	public boolean existePorNome( String nome );
	
	@Query("select p from Profissional p where lower_unaccent(p.nome)=lower_unaccent(?1)")
	public Optional<Profissional> buscaPorNome( String nome );
	
	@Query("select p from Profissional p where lower_unaccent(p.nome) like lower_unaccent(?1)")
	public List<Profissional> filtraPorNome( String nomeIni );
		
	@Query("select p from Profissional p where p.usuario.id=?1")
	public Optional<Profissional> buscaPorUsuario( Long uid );
	
	@Query("select p "
			+ "from Profissional p "
				+ "join p.usuario u "
				+ "join UsuarioClinicaVinculo v "
			+ "where v.usuario.id=u.id and v.clinica.id=?1")
	public List<Profissional> listaPorClinica( Long clinicaId );
	
	@Query("select p "
			+ "from Profissional p "
				+ "join p.usuario u "
				+ "join UsuarioClinicaVinculo v "
			+ "where v.usuario.id=u.id and v.clinica.id=?1 and p.usuario.id=?2")
	public List<Profissional> listaPorClinicaEUsuario( Long clinicaId, Long usuarioId );
	
	@Query("select p "
			+ "from Profissional p "
				+ "join p.usuario u "
				+ "join UsuarioClinicaVinculo v "
			+ "where "
				+ "v.usuario.id=u.id and "
		 		+ "v.clinica.id=?1 and "
		 		+ "lower_unaccent(p.nome) like lower_unaccent(?2)")
	public List<Profissional> filtra( Long clinicaId, String nomeIni );

	@Query("select p "
			+ "from Profissional p "
			+ "where "
		 		+ "lower_unaccent(p.nome) like lower_unaccent(?1)")
	public List<Profissional> filtraTodos( String nomeIni );
				
	@Query("select p "
		 + "from Profissional p "
		 	+ "join p.usuario u "
		 	+ "join UsuarioClinicaVinculo v "
		 + "where "
		 	+ "v.usuario.id=u.id and p.id=?1 and v.clinica.id in (?2)")
	public Optional<Profissional> busca( Long profissionalId, Long[] clinicasIDs );
	
	@Modifying
	@Query("delete from Profissional p where p.id in (select p.id from Profissional p, UsuarioClinicaVinculo v where v.usuario.id=p.usuario.id and v.clinica.id=?1)")
	public void deletaPorClinica( Long clinicaId );

}
