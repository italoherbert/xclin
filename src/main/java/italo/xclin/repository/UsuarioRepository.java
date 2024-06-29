package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where lower_unaccent(u.username)=lower_unaccent(?1) and u.senha=?2")
	public Optional<Usuario> buscaPorLogin( String username, String senha );
	
	@Query("select u from Usuario u where lower_unaccent(u.username)=lower_unaccent(?1)")
	public Optional<Usuario> buscaPorUsername( String username ); 
	
	@Query("select count(*)=1 from Usuario u where lower_unaccent(u.username)=lower_unaccent(?1)")
	public boolean existePorUsername( String username ); 
		
	@Query("select u from Usuario u where lower_unaccent(u.username) like lower_unaccent(?1)")
	public List<Usuario> buscaPorUsernameIni( String usernameIni );

	@Query("select u "
			+ "from Usuario u "
				+ "join UsuarioClinicaVinculo v "
			+ "where v.usuario.id=u.id and v.clinica.id=?1")
	public List<Usuario> listaPorClinica( Long clinicaId );
	
}
