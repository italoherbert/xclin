package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.xclin.model.DiretorClinicaVinculo;

public interface DiretorClinicaVinculoRepository extends JpaRepository<DiretorClinicaVinculo, Long> {

	void deleteByDiretorId( Long diretorId );
	
}
