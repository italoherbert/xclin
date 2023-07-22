package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	@Query("select count(*)>0 "
			+ "from Consulta c "
				+ "join Orcamento o "
				+ "join Atendimento a "
			+ "where c.orcamento.id=o.id and a.orcamento.id=o.id and "
				+ "c.id=?1 and "
				+ "a.clinica.id in (?2)")
	public boolean consultaDeClinica( Long consultaId, Long[] clinicasIDs );
	
}
