package italo.scm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {

	@Query("select count(*)=1 from Recurso r where lower(r.nome)=lower(?1)")
	public boolean existePorNome( String nome );
	
	@Query("select r from Recurso r where lower(r.nome)=lower(?1)")
	public Optional<Recurso> buscaPorNome( String nome );
	
	@Query("select r from Recurso r where lower(r.nome) like lower(?1)")
	public List<Recurso> filtra( String nomeIni );
	
	public List<Recurso> findAllByOrderByIdDesc();
	
}
