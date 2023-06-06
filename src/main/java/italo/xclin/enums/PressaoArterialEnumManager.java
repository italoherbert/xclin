package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.PressaoArterial;

@Component
public class PressaoArterialEnumManager extends AbstractEnumManager<PressaoArterial>{

	@Override
	protected PressaoArterial[] values() {
		return PressaoArterial.values();
	}

	@Override
	protected String label(PressaoArterial p) {
		return p.label();
	}

}
