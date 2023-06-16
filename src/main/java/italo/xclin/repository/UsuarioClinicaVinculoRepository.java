package italo.xclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.xclin.model.UsuarioClinicaVinculo;

public interface UsuarioClinicaVinculoRepository extends JpaRepository<UsuarioClinicaVinculo, Long> {

	@Query("select count(*)>0 "
			+ "from UsuarioClinicaVinculo v "
			+ "where v.usuario.id=?1 and v.clinica.id=?2")
	public boolean existe( Long usuarioId, Long clinicaId );
	
}
