package italo.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.scm.model.DiretorClinicaVinculo;

public interface DiretorClinicaVinculoRepository extends JpaRepository<DiretorClinicaVinculo, Long> {

	void deleteByDiretorId( Long diretorId );
	
}
