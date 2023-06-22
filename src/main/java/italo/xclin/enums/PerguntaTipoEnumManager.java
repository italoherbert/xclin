package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.PerguntaTipo;

@Component
public class PerguntaTipoEnumManager extends AbstractEnumManager<PerguntaTipo>{

	@Override
	protected PerguntaTipo[] values() {
		return PerguntaTipo.values();
	}

	@Override
	protected String label(PerguntaTipo pt) {
		return pt.label();
	}

}
