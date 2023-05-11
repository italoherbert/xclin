package italo.scm.enums;

import org.springframework.stereotype.Component;

import italo.scm.enums.tipos.Nacionalidade;

@Component
public class NacionalidadeEnumManager extends AbstractEnumManager<Nacionalidade> {

	@Override
	protected Nacionalidade[] values() {
		return Nacionalidade.values();
	}

	@Override
	protected String label(Nacionalidade nacionalidade) {
		return nacionalidade.label();
	}
	
}