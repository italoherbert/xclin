package italo.xclin.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.enums.tipos.ConsultaStatus;
import italo.xclin.enums.tipos.Turno;
import italo.xclin.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	@Query("select day(c.dataAtendimento), "
				+ "sum(case when c.turno='MANHA' then 1 else 0 end), "
				+ "sum(case when c.turno='TARDE' then 1 else 0 end), "
				+ "sum(case when c.turno='NOITE' then 1 else 0 end) "
			+ "from Consulta c "
				+ "join Profissional p "
				+ "join ProfissionalClinicaVinculo v "				
			+ "where "
				+ "v.clinica.id=?1 and p.id=?2 and "
				+ "month(c.dataAtendimento)=?3 and year(c.dataAtendimento)=?4 "
			+ "group by (day(c.dataAtendimento)) "
			+ "order by day(c.dataAtendimento)")
	public List<Object[]> agrupaPorDiaDeMes( 
			Long clinicaId, Long profissionalId, int mes, int ano );
		
	@Query("select c "
			+ "from Consulta c "
				+ "join Profissional pr "
				+ "join Paciente pa "
				+ "join ProfissionalClinicaVinculo v "
			+ "where v.clinica.id=?1 and "
				+ "c.dataAtendimento between ?12 and ?13 and "
				+ "(?6=false or (lower(pa.nome) like lower(?2))) and "
				+ "(?7=false or (lower(pr.nome) like lower(?3))) and "
				+ "(?8=true or c.turno=?4) and "
				+ "(?9=true or c.status=?5) and "
				+ "(?10=true or c.paga=false) and "
				+ "(?11=true or c.retorno=false)")
	public List<Consulta> filtra( 
			Long clinicaId, 
			String pacienteNomeIni, String profissionalNomeIni, 
			Turno turno, ConsultaStatus status, 
			boolean incluirPaciente, boolean incluirProfissional, 
			boolean incluirTodosTurnos, boolean incluirTodosStatus, 
			boolean incluirPagas, boolean incluirRetornos, 
			Date dataInicio, Date dataFim );
	
	@Query("select c "
			+ "from Consulta c "
				+ "join ProfissionalClinicaVinculo v "
			+ "where "
				+ "c.clinica.id=?1 and "
				+ "v.profissional.id=?2 and "
				+ "c.dataAtendimento=?3 and "
				+ "c.turno=?4 and "
				+ "c.status=?5 "
			+ "order by c.dataAgendamento asc")
	public List<Consulta> listaFilaPorStatus( 
			Long clinicaId, Long profissionalId, 
			Date data, 
			Turno turno, 
			ConsultaStatus status );
	
	@Query("select c "
			+ "from Consulta c "
				+ "join ProfissionalClinicaVinculo v "
			+ "where "
				+ "c.clinica.id=?1 and "
				+ "v.profissional.id=?2 and "
				+ "c.dataAtendimento=?3 and "
				+ "c.turno=?4 "
			+ "order by c.dataAgendamento asc")
	public List<Consulta> listaFilaCompleta( 
			Long clinicaId, Long profissionalId,
			Date data,
			Turno turno );
	

	@Query("select count(*) "
			+ "from Consulta c "
			+ "where c.clinica.id=?1 and "
				+ "c.profissional.id=?2 and "
				+ "date(c.dataAtendimento)=current_date and "
				+ "c.turno=?3 and "
				+ "(c.status='REGISTRADA' or c.status='INICIADA')")
	public int contaFila( Long clinicaId, Long profissionalId, Turno turno );
	
	@Query( "select c "
			+ "from Consulta c "
			+ "where c.status='INICIADA' and "
				+ "c.clinica.id=?1 and "
				+ "c.profissional.id=?2 and "
				+ "date(c.dataAtendimento)=current_date and "
				+ "c.turno=?3")
	public Optional<Consulta> getIniciada(
			Long clinicaId, Long profissionalId, 
			Turno turno);
		
	@Query("select c "
			+ "from Consulta c "
			+ "where c.clinica.id=?1 and c.profissional.id=?2 and c.paciente.id=?3 "
			+ "order by c.dataSaveObservacoes desc")
	public List<Consulta> getUltimasObservacoes( 
			Long clinicaId, Long profissionalId, Long pacienteId, Pageable p );
		
	@Modifying
	@Query( "update Consulta c set "
			+ 	"status=(case when c.status='INICIADO' then 'FINALIDADO' else c.status end) "
			+ "where "
				+ "c.clinica.id=?1 and "
				+ "c.profissional.id=?2 and "
				+ "date(c.dataAtendimento)=current_date and "
				+ "c.turno=?3")
	public void finalizaConsultasIniciadas( 
			Long clinicaId, Long profissionalId, 
			Turno turno );
	
}
