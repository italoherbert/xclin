package italo.scm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.scm.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	@Query("select day(c.dataHoraAgendamento), count(*) "
			+ "from Consulta c "
				+ "join Profissional p "
				+ "join ProfissionalClinicaVinculo v "				
			+ "where "
				+ "v.clinica.id=?1 and p.id=?2 and "
				+ "month(c.dataHoraAgendamento)=?3 and year(c.dataHoraAgendamento)=?4 "
			+ "group by (day(c.dataHoraAgendamento)) "
			+ "order by day(c.dataHoraAgendamento)")
	public List<Object[]> agrupaPorDiaDeMes( 
			Long clinicaId, Long profissionalId, int mes, int ano );
	
	@Query("select c "
			+ "from Consulta c "
				+ "join Profissional p "
				+ "join ProfissionalClinicaVinculo v "
			+ "where "
				+ "v.clinica.id=?1 and p.id=?2 and "
				+ "day(c.dataHoraAgendamento)=?3 and month(c.dataHoraAgendamento)=?4 and year(c.dataHoraAgendamento)=?5 "
			+ "order by c.dataHoraAgendamento")
	public List<Consulta> listaPorDia( Long clinicaId, Long profissionalId, int dia, int mes, int ano );
	
}
