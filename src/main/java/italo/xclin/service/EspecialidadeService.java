package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.EspecialidadeLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Especialidade;
import italo.xclin.model.request.filtro.EspecialidadeFiltroRequest;
import italo.xclin.model.request.save.EspecialidadeSaveRequest;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.load.edit.EspecialidadeEditLoadResponse;
import italo.xclin.model.response.load.reg.EspecialidadeRegLoadResponse;
import italo.xclin.model.response.load.tela.EspecialidadeTelaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private EspecialidadeLoader especialidadeLoader;
	
	public void registra( Long clinicaId, EspecialidadeSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		boolean existe = especialidadeRepository.existePorNome( clinicaId, nome );
		if ( existe )
			throw new ServiceException( Erro.ESPECIALIDADE_JA_EXISTE );
		
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		Especialidade e = especialidadeLoader.novoBean( clinica );
		especialidadeLoader.loadBean( e, request );
		especialidadeRepository.save( e );
	}
	
	public void altera( Long id, EspecialidadeSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		Optional<Especialidade> rop = especialidadeRepository.findById( id );
		if ( !rop.isPresent() )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		Especialidade e = rop.get();
		
		Clinica c = e.getClinica();
		Long clinicaId = c.getId();
		
		if ( !nome.equalsIgnoreCase( e.getNome() ) ) {
			boolean existe = especialidadeRepository.existePorNome( clinicaId, nome );
			if ( existe )
				throw new ServiceException( Erro.ESPECIALIDADE_JA_EXISTE );
		}
		
		especialidadeLoader.loadBean( e, request );
		especialidadeRepository.save( e );
	}
	
	public List<EspecialidadeResponse> filtra( Long clinicaId, EspecialidadeFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		
		List<Especialidade> especialidades;
		if ( nomeIni.equals( "*" ) ) {
			especialidades = especialidadeRepository.listaPorClinica( clinicaId ); 		
		} else {
			especialidades = especialidadeRepository.filtra( clinicaId, nomeIni+"%" );
		}
		
		List<EspecialidadeResponse> lista = new ArrayList<>();
		for( Especialidade e : especialidades ) {
			EspecialidadeResponse resp = especialidadeLoader.novoResponse();
			especialidadeLoader.loadResponse( resp, e );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public List<EspecialidadeResponse> listaTodas( Long clinicaId ) throws ServiceException {
		List<Especialidade> especialidades = especialidadeRepository.listaPorClinica( clinicaId );
		List<EspecialidadeResponse> lista = new ArrayList<>();
		for( Especialidade e : especialidades ) {
			EspecialidadeResponse resp = especialidadeLoader.novoResponse();
			especialidadeLoader.loadResponse( resp, e );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public EspecialidadeResponse get( Long id ) throws ServiceException {
		Optional<Especialidade> rop = especialidadeRepository.findById( id );
		if ( !rop.isPresent() )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		Especialidade e = rop.get();
		
		EspecialidadeResponse resp = especialidadeLoader.novoResponse();
		especialidadeLoader.loadResponse( resp, e );
		return resp;
	}
	
	public EspecialidadeTelaLoadResponse loadTela( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
			
		return especialidadeLoader.novoTelaLoadResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public EspecialidadeRegLoadResponse loadRegTela( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
			
		return especialidadeLoader.novoRegLoadResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public EspecialidadeEditLoadResponse loadEditTela( Long especialidadeId ) throws ServiceException {
		Optional<Especialidade> especialidadeOp = especialidadeRepository.findById( especialidadeId );
		if ( !especialidadeOp.isPresent() )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		Especialidade esp = especialidadeOp.get();
		Clinica clinica = esp.getClinica();
		
		EspecialidadeResponse eresp = especialidadeLoader.novoResponse();
		especialidadeLoader.loadResponse( eresp, esp );
			
		return especialidadeLoader.novoEditLoadResponse( clinica, eresp );
	}
	
	public void deleta( Long id ) throws ServiceException {
		boolean existe = especialidadeRepository.existsById( id );
		if ( !existe )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		especialidadeRepository.deleteById( id ); 
	}
	
}
