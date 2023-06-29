package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.ExameStatus;

@Component
public class ExameStatusEnumManager extends AbstractEnumManager<ExameStatus> {

	@Override
	protected ExameStatus[] values() {
		return ExameStatus.values();
	}

	@Override
	protected String label(ExameStatus e) {
		return e.label();
	}

}
