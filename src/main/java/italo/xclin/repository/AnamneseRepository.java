package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.xclin.model.Anamnese;

public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {
	
}
