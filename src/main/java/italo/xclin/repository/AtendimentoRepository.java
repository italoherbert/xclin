package italo.xclin.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.enums.tipos.AtendimentoStatus;
import italo.xclin.enums.tipos.Turno;
import italo.xclin.model.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	@Query("select day(a.dataAtendimento), "
				+ "sum(case when a.turno='MANHA' then 1 else 0 end), "
				+ "sum(case when a.turno='TARDE' then 1 else 0 end), "
				+ "sum(case when a.turno='NOITE' then 1 else 0 end) "
			+ "from Atendimento a "							
			+ "where "
				+ "a.clinica.id=?1 and a.profissional.id=?2 and "
				+ "month(a.dataAtendimento)=?3 and year(a.dataAtendimento)=?4 "
			+ "group by (day(a.dataAtendimento)) "
			+ "order by day(a.dataAtendimento)")
	public List<Object[]> agrupaPorDiaDeMes( 
			Long clinicaId, Long profissionalId, int mes, int ano );
		
	@Query("select a "
			+ "from Atendimento a "
			+ "where a.clinica.id=?1 and "
				+ "a.dataAtendimento between ?10 and ?11 and "
				+ "(?6=false or (lower(a.paciente.nome) like lower(?2))) and "
				+ "(?7=false or (lower(a.profissional.nome) like lower(?3))) and "
				+ "(?8=true or a.turno=?4) and "
				+ "(?9=true or a.status=?5)")
	public List<Atendimento> filtra( 
			Long clinicaId, 
			String pacienteNomeIni, String profissionalNomeIni, 
			Turno turno, AtendimentoStatus status, 
			boolean incluirPaciente, boolean incluirProfissional, 
			boolean incluirTodosTurnos, boolean incluirTodosStatus, 
			Date dataInicio, Date dataFim );
	
	@Query("select a "
			+ "from Atendimento a "
			+ "where "
				+ "a.clinica.id=?1 and "
				+ "a.profissional.id=?2 and "
				+ "a.dataAtendimento=?3 and "
				+ "a.turno=?4 and "
				+ "a.status=?5 "
			+ "order by a.dataAgendamento asc")
	public List<Atendimento> listaFilaPorStatus( 
			Long clinicaId, Long profissionalId, 
			Date data, 
			Turno turno, 
			AtendimentoStatus status );
	
	@Query("select a "
			+ "from Atendimento a "
			+ "where "				
				+ "a.clinica.id=?1 and "
				+ "a.profissional.id=?2 and "
				+ "a.dataAtendimento=?3 and "
				+ "a.turno=?4 "
			+ "order by a.dataAgendamento asc")
	public List<Atendimento> listaFilaCompleta( 
			Long clinicaId, Long profissionalId,
			Date data,
			Turno turno );
	

	@Query("select count(*) "
			+ "from Atendimento a "
			+ "where a.clinica.id=?1 and "
				+ "a.profissional.id=?2 and "
				+ "date(a.dataAtendimento)=current_date and "
				+ "a.turno=?3 and "
				+ "(a.status='REGISTRADO' or a.status='INICIADO')")
	public int contaFila( Long clinicaId, Long profissionalId, Turno turno );
	
	@Query( "select a "
			+ "from Atendimento a "
			+ "where a.status='INICIADO' and "
				+ "a.clinica.id=?1 and "
				+ "a.profissional.id=?2 and "
				+ "date(a.dataAtendimento)=current_date and "
				+ "a.turno=?3")
	public Optional<Atendimento> getIniciada(
			Long clinicaId, Long profissionalId, 
			Turno turno);
		
	@Query("select a "
			+ "from Atendimento a "
			+ "where a.clinica.id=?1 and a.profissional.id=?2 and a.paciente.id=?3 "
			+ "order by a.dataSaveObservacoes desc")
	public List<Atendimento> getUltimasObservacoes( 
			Long clinicaId, Long profissionalId, Long pacienteId, Pageable p );
		
	@Modifying
	@Query( "update Atendimento a set "
			+ 	"status=(case when a.status='INICIADO' then 'FINALIDADO' else a.status end) "
			+ "where "
				+ "a.clinica.id=?1 and "
				+ "a.profissional.id=?2 and "
				+ "date(a.dataAtendimento)=current_date and "
				+ "a.turno=?3")
	public void finalizaAtendimentosIniciadas( 
			Long clinicaId, Long profissionalId, 
			Turno turno );
	
}
