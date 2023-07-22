package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.ExameItem;

public interface ExameItemRepository extends JpaRepository<ExameItem, Long> {

	@Query("select count(*)>0 "
			+ "from ExameItem ei "
				+ "join Orcamento o "
				+ "join Atendimento a "
			+ "where ei.orcamento.id=o.id and a.orcamento.id=o.id and "
				+ "ei.id=?1 and "
				+ "a.clinica.id in (?2)")
	public boolean exameItemDeClinica( Long exameItemId, Long[] clinicasIDs );
	
}
