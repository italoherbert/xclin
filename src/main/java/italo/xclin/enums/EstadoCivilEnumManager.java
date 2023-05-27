package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.EstadoCivil;

@Component
public class EstadoCivilEnumManager extends AbstractEnumManager<EstadoCivil> {

	@Override
	protected EstadoCivil[] values() {
		return EstadoCivil.values();
	}

	@Override
	protected String label(EstadoCivil e) {
		return e.label();
	} 

}
