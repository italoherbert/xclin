package italo.xclin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	@Query("select l "
			+ "from Lancamento l "
			+ "where l.clinica.id=?1"
			  + "(date(l.dataLancamento) between ?2 and ?3) and "
			+ 	"(?4=false or (?4=true and l.usuario.username like ?5) ) "
			+ "order by l.dataLancamento asc")
	public List<Lancamento> filtra( 
			Long clinicaId,
			Date dataIni, Date dataFim, 
			boolean incluirUsername, 
			String filtroUsername );
	
	@Query("select l "
			+ "from Lancamento l "
			+ "where l.clinica.id=?1 and date(l.dataLancamento)=date(?2)") 
	public List<Lancamento> clinicaLancamentosDoDia( Long clinicaId, Date dataDia );
	
	@Query("select l "
			+ "from Lancamento l "
			+ "where "
				+ "l.clinica.id=?1 and "
				+ "l.usuario.id=?2 and "
				+ "date(l.dataLancamento)=date(?3)") 	
	public List<Lancamento> clinicaUsuarioLancamentosDoDia(
			Long clinicaId, long usuarioId, Date dataDia );
	
}
