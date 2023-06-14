package italo.xclin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	@Query("select l "
			+ "from Lancamento l "
			+ "where (date(l.dataLancamento) between ?1 and ?2) and "
			+ 	"(?3=false or (?3=true and l.usuario.username like ?4) ) "
			+ "order by l.dataLancamento asc")
	public List<Lancamento> filtra( 
			Date dataIni, Date dataFim, 
			boolean incluirUsername, 
			String filtroUsername );
	
}
