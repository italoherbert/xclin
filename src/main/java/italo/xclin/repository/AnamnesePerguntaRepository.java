package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.xclin.model.AnamnesePergunta;

public interface AnamnesePerguntaRepository extends JpaRepository<AnamnesePergunta, Long>{

	public void deleteByAnamneseId( Long anamneseId );
	
}
