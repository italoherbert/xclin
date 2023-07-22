package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.Procedimento;
import italo.xclin.model.ProcedimentoItem;
import italo.xclin.model.request.save.ProcedimentoItemSaveRequest;
import italo.xclin.model.response.ProcedimentoItemResponse;

@Component
public class ProcedimentoItemLoader {

	public void loadBean( ProcedimentoItem proc, ProcedimentoItemSaveRequest request ) throws LoaderException {		
		proc.setValor( request.getValor() );
		proc.setConcluido( false ); 	
	}
	
	public void loadResponse( ProcedimentoItemResponse resp, ProcedimentoItem procedimento ) {
		resp.setId( procedimento.getId() );
		resp.setNome( procedimento.getNome() );
		resp.setDescricao( procedimento.getDescricao() );
		resp.setValor( procedimento.getValor() );
		resp.setConcluido( procedimento.isConcluido() ); 
	}
		
	public ProcedimentoItem novoBean( Procedimento procedimento ) {
		ProcedimentoItem item = new ProcedimentoItem();
		item.setNome( procedimento.getNome() );
		item.setDescricao( procedimento.getDescricao() );
		return item;
	}
	
	public ProcedimentoItemResponse novoResponse() {
		return new ProcedimentoItemResponse();
	}
	
	
}
