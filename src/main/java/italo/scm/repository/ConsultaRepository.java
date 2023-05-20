package italo.scm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.enums.tipos.ConsultaStatus;
import italo.scm.enums.tipos.Turno;
import italo.scm.model.Consulta;

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
				+ "join Clinica cl "
				+ "join ProfissionalClinicaVinculo v "
			+ "where "
				+ "cl.id=?1 and "
				+ "v.profissional.id=?2 and "
				+ "c.dataAtendimento=?3 and "
				+ "c.turno=?4 and "
				+ "c.status=?5 "
			+ "order by c.dataAgendamento asc")
	public List<Consulta> listaFila( 
			Long clinicaId, Long profissionalId, Date data, Turno turno, ConsultaStatus status );
	
}
