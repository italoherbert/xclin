package italo.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.scm.model.ProfissionalClinicaVinculo;

public interface ProfissionalClinicaVinculoRepository extends JpaRepository<ProfissionalClinicaVinculo, Long>{
	
	void deleteByProfissionalId( Long profissionalId );

}
