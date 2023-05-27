package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.xclin.model.ProfissionalClinicaVinculo;

public interface ProfissionalClinicaVinculoRepository extends JpaRepository<ProfissionalClinicaVinculo, Long>{
	
	void deleteByProfissionalId( Long profissionalId );

}
