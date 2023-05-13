package italo.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.scm.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
