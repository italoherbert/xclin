package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Consulta;
import italo.xclin.model.ExameItem;
import italo.xclin.model.Orcamento;
import italo.xclin.model.ProcedimentoItem;
import italo.xclin.model.request.save.OrcamentoPagamentoSaveRequest;
import italo.xclin.model.request.save.OrcamentoSaveRequest;
import italo.xclin.model.response.ConsultaResponse;
import italo.xclin.model.response.ExameItemResponse;
import italo.xclin.model.response.OrcamentoResponse;
import italo.xclin.model.response.ProcedimentoItemResponse;
import italo.xclin.model.response.load.edit.OrcamentoPagamentoLoadResponse;

@Component
public class OrcamentoLoader {

	public void loadBean( Orcamento orcamento, OrcamentoSaveRequest request ) {
		orcamento.setTemConsulta( request.isTemConsulta() );
		orcamento.setValorTotal( request.getValorTotal() );
		orcamento.setValorPago( request.getValorPago() );
		orcamento.setPago( request.isPago() );
	}
	
	public void loadBean( Orcamento o, OrcamentoPagamentoSaveRequest request ) {
		o.setValorPago( o.getValorPago() + request.getValorPago() );
		o.setPago( request.isPago() );
	}
	
	public void loadResponse( OrcamentoResponse resp, Orcamento orcamento ) {
		resp.setTemConsulta( orcamento.isTemConsulta() );
		resp.setPago( orcamento.isPago() );
		resp.setValorTotal( orcamento.getValorTotal() );
		resp.setValorPago( orcamento.getValorPago() );		
	}
	
	public Orcamento novoBean(
			Consulta consulta,
			List<ExameItem> exames,
			List<ProcedimentoItem> procedimentos ) {
		
		Orcamento orcamento = new Orcamento();
		
		orcamento.setConsulta( consulta );
		orcamento.setExames( exames );
		orcamento.setProcedimentos( procedimentos );
		
		if ( consulta != null )
			consulta.setOrcamento( orcamento );
		exames.forEach( e -> e.setOrcamento( orcamento ) );
		procedimentos.forEach( p -> p.setOrcamento( orcamento ) );
		
		return orcamento;
	}
	
	public OrcamentoResponse novoResponse(
			ConsultaResponse consulta,
			List<ExameItemResponse> exames,
			List<ProcedimentoItemResponse> procedimentos ) {
		
		OrcamentoResponse resp = new OrcamentoResponse();
		resp.setConsulta( consulta );
		resp.setExames( exames );
		resp.setProcedimentos( procedimentos );
		resp.setValorTotalBruto( this.valorTotalBruto( consulta, exames, procedimentos ) );
		return resp;
	}
	
	public OrcamentoPagamentoLoadResponse novoPagamentoResponse( 
			Orcamento o,
			Consulta c, 
			List<ExameItem> exames, 
			List<ProcedimentoItem> procedimentos ) {		
		
		OrcamentoPagamentoLoadResponse resp = new OrcamentoPagamentoLoadResponse();
		resp.setPago( o.isPago() );
		resp.setValorPago( o.getValorPago() );
		resp.setValorTotal( o.getValorTotal() ); 
		resp.setValorTotalBruto( this.valorTotalBruto( c, exames, procedimentos ) ); 
		return resp;
	}
	
	private double valorTotalBruto( 
			ConsultaResponse consulta, 
			List<ExameItemResponse> exames,
			List<ProcedimentoItemResponse> procedimentos ) {
		
		double valorTotal = 0;
		
		if ( consulta != null )
			valorTotal += consulta.getValor();
		
		for( ExameItemResponse exame : exames )
			valorTotal += exame.getValor();
		
		for( ProcedimentoItemResponse proc : procedimentos )
			valorTotal += proc.getValor();
		
		return valorTotal;
	}
	
	private double valorTotalBruto( 
			Consulta consulta, 
			List<ExameItem> exames, 
			List<ProcedimentoItem> procedimentos ) {
		
		double valorTotal = 0;
		
		if ( consulta != null )
			valorTotal += consulta.getValor();
		
		for( ExameItem exame : exames )
			valorTotal += exame.getValor();
		
		for( ProcedimentoItem proc : procedimentos )
			valorTotal += proc.getValor();
		
		return valorTotal;
	}
	
}
