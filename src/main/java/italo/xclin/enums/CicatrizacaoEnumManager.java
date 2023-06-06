package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.Cicatrizacao;

@Component
public class CicatrizacaoEnumManager extends AbstractEnumManager<Cicatrizacao> {

	@Override
	protected Cicatrizacao[] values() {
		return Cicatrizacao.values();
	}

	@Override
	protected String label(Cicatrizacao c) {
		return c.label();
	}

}
