package italo.scm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
				+ "join Profissional p "
				+ "join ProfissionalClinicaVinculo v "
			+ "where "
				+ "v.clinica.id=?1 and p.id=?2 and "
				+ "day(c.dataAtendimento)=?3 and month(c.dataAtendimento)=?4 and year(c.dataAtendimento)=?5 "
			+ "order by c.dataAgendamento")
	public List<Consulta> listaPorDia( Long clinicaId, Long profissionalId, int dia, int mes, int ano );
	
}
