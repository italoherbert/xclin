package italo.scm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.username=?1 and u.senha=?2")
	public Optional<Usuario> buscaPorLogin( String username, String senha );
	
	@Query("select u from Usuario u where lower(u.username)=lower(?1)")
	public Optional<Usuario> buscaPorUsername( String username ); 
	
	@Query("select count(*)=1 from Usuario u where lower(u.username)=lower(?1)")
	public boolean existePorUsername( String username ); 
		
	@Query("select u from Usuario u where lower(u.username) like lower(?1)")
	public List<Usuario> buscaPorUsernameIni( String usernameIni );
	
}
