package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

	@Query("select count(*)>0 "
			+ "from Atendimento a "
			+ "where a.orcamento.id=?1")
	public boolean vinculadoComAtendimento( Long orcamentoId );
	
}
