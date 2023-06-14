package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.LancamentoTipo;

@Component
public class LancamentoTipoEnumManager extends AbstractEnumManager<LancamentoTipo>{

	@Override
	protected LancamentoTipo[] values() {
		return LancamentoTipo.values();
	}

	@Override
	protected String label(LancamentoTipo l) {
		return l.label();
	}

}
