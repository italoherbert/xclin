package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.ProcedimentoItem;

public interface ProcedimentoItemRepository extends JpaRepository<ProcedimentoItem, Long> {

	@Query("select count(*)>0 "
			+ "from ProcedimentoItem pi "
				+ "join Orcamento o "
				+ "join Atendimento a "
			+ "where pi.orcamento.id=o.id and a.orcamento.id=o.id and "
				+ "pi.id=?1 and "
				+ "a.clinica.id in (?2)")
	public boolean procedimentoItemDeClinica( Long procedimentoItemId, Long[] clinicasIDs );
	
}
