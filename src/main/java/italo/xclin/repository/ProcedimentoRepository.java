package italo.xclin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

	@Query("select count(*)>0 "
			+ "from Procedimento p "
			+ "where p.clinica.id=?1 and lower_unaccent(p.nome)=lower_unaccent(?2)")
	public boolean existePorClinicaPorNome( Long clinicaId, String nome );
		
	public List<Procedimento> findByClinicaId( Long clinicaId );
	
	@Query("select p "
			+ "from Procedimento p "
			+ "where p.clinica.id in (?1)")
	public List<Procedimento> listaPorClinicasIDs( Long[] clinicasIDs );
	
}
