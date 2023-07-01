package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.Exame;
import italo.xclin.model.ExameItem;
import italo.xclin.model.request.save.ExameItemSaveRequest;
import italo.xclin.model.response.ExameItemResponse;

@Component
public class ExameItemLoader {
	
	public void loadBean( ExameItem exame, ExameItemSaveRequest request ) throws LoaderException {		
		exame.setValor( request.getValor() );		
	}
	
	public void loadResponse( ExameItemResponse resp, ExameItem exame ) {
		resp.setId( exame.getId() );
		resp.setNome( exame.getNome() );
		resp.setDescricao( exame.getDescricao() );
		resp.setValor( exame.getValor() ); 
	}
		
	public ExameItem novoBean( Exame exame ) {
		ExameItem item = new ExameItem();
		item.setNome( exame.getNome() );
		item.setDescricao( exame.getDescricao() );
		return item;
	}
	
	public ExameItemResponse novoResponse() {
		return new ExameItemResponse();
	}
	
}