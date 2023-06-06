package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.Sangramento;

@Component
public class SangramentoEnumManager extends AbstractEnumManager<Sangramento> {

	@Override
	protected Sangramento[] values() {
		return Sangramento.values();
	}

	@Override
	protected String label(Sangramento s) {
		return s.label();
	}

}
