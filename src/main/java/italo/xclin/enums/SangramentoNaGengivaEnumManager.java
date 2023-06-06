package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.SangramentoNaGengiva;

@Component
public class SangramentoNaGengivaEnumManager extends AbstractEnumManager<SangramentoNaGengiva> {

	@Override
	protected SangramentoNaGengiva[] values() {
		return SangramentoNaGengiva.values();
	}

	@Override
	protected String label(SangramentoNaGengiva s) {
		return s.label();
	}

}
