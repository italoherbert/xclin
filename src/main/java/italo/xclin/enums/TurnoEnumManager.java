package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.Turno;

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
