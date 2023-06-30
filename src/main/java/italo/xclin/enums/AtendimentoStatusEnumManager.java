package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.AtendimentoStatus;

@Component
public class AtendimentoStatusEnumManager extends AbstractEnumManager<AtendimentoStatus> {

	@Override
	protected AtendimentoStatus[] values() {
		return AtendimentoStatus.values();
	}

	@Override
	protected String label(AtendimentoStatus status) {
		return status.label();
	} 

}
