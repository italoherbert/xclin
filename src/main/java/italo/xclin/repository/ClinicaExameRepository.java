package italo.xclin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.ClinicaExame;

public interface ClinicaExameRepository extends JpaRepository<ClinicaExame, Long> {

	@Query("select count(*)>0 "
			+ "from ClinicaExame e "
			+ "where e.clinica.id=?1 and lower_unaccent(e.nome)=lower_unaccent(?2)")
	public boolean existePorClinicaPorNome( Long clinicaId, String nome );
	
	public List<ClinicaExame> findByClinicaId( Long clinicaId );
	
}
