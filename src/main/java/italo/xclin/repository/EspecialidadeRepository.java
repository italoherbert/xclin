package italo.xclin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

	@Query("select count(*)=1 from Especialidade e where lower(e.nome)=lower(?1)")
	public boolean existePorNome( String nome );
	
	@Query("select e from Especialidade e where lower(e.nome)=lower(?1)")
	public Optional<Especialidade> buscaPorNome( String nome );
	
	@Query("select e from Especialidade e where lower(e.nome) like lower(?1)")
	public List<Especialidade> filtra( String nomeIni );
	
	@Query("select e "
			+ "from Especialidade e "
				+ "join ProfissionalEspecialidadeVinculo v "
			+ "where v.profissional.id=?1")
	public List<Especialidade> listaPorProfissional( Long profissionalId );
	
}
