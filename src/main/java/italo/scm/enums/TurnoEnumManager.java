package italo.scm.enums;

import org.springframework.stereotype.Component;

import italo.scm.enums.tipos.Turno;

@Component
public class TurnoEnumManager extends AbstractEnumManager<Turno> {

	@Override
	protected Turno[] values() {
		return Turno.values();
	}

	@Override
	protected String label(Turno t) {
		return t.label();
	}

}
