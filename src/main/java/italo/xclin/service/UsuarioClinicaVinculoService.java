package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.UsuarioClinicaVinculoLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.model.response.UsuarioClinicaVinculoResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.UsuarioClinicaVinculoRepository;
import italo.xclin.repository.UsuarioRepository;

@Service
public class UsuarioClinicaVinculoService {

	@Autowired
	private UsuarioClinicaVinculoRepository usuarioClinicaVinculoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private UsuarioClinicaVinculoLoader usuarioClinicaVinculoLoader;
			
	public void vincula( Long usuarioId, Long clinicaId ) throws ServiceException {
		boolean existe = usuarioClinicaVinculoRepository.existe( usuarioId, clinicaId );
		if ( existe )
			throw new ServiceException( Erro.VINCULO_USUARIO_CLINICA_JA_EXISTE );
		
		Optional<Usuario> usuarioOp = usuarioRepository.findById( usuarioId );
		if ( !usuarioOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Usuario u = usuarioOp.get();
		Clinica c = clinicaOp.get();
		
		UsuarioClinicaVinculo vinculo = new UsuarioClinicaVinculo();
		vinculo.setUsuario( u );
		vinculo.setClinica( c );
		
		usuarioClinicaVinculoRepository.save( vinculo );		
	}
	
	public List<UsuarioClinicaVinculoResponse> listaPorUsuario( Long usuarioId ) throws ServiceException {
		Optional<Usuario> usuarioOp = usuarioRepository.findById( usuarioId );
		if ( !usuarioOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		Usuario u = usuarioOp.get();
		
		List<UsuarioClinicaVinculoResponse> lista = new ArrayList<>();
		
		List<UsuarioClinicaVinculo> vinculos = u.getUsuarioClinicaVinculos();
		for( UsuarioClinicaVinculo vinculo : vinculos ) {
			Clinica c = vinculo.getClinica();

			UsuarioClinicaVinculoResponse resp = usuarioClinicaVinculoLoader.novoResponse( u, c );
			usuarioClinicaVinculoLoader.loadResponse( resp, vinculo );
			
			lista.add( resp );
		}
		
		return lista;
	}
	
	public void deleta( Long vinculoId ) throws ServiceException {
		boolean existe = usuarioClinicaVinculoRepository.existsById( vinculoId );
		if ( !existe )
			throw new ServiceException( Erro.VINCULO_USUARIO_CLINICA_NAO_ENCONTRADO );
		
		usuarioClinicaVinculoRepository.deleteById( vinculoId );
	}
	
}
