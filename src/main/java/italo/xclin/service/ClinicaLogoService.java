package italo.xclin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.model.Clinica;
import italo.xclin.model.request.save.ClinicaLogoSaveRequest;
import italo.xclin.model.response.Base64ImageResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.service.shared.ClinicaLogoSharedService;

@Service
public class ClinicaLogoService {
    
    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private ClinicaLogoSharedService clinicaLogoSharedService;

    public void salvaLogo( Long clinicaId, ClinicaLogoSaveRequest request ) throws ServiceException {
        Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );

		Clinica clinica = clinicaOp.get();
		clinica.setLogomarca( request.getLogo() );
        clinicaRepository.save( clinica );
    }

    public Base64ImageResponse getLogo( Long clinicaId ) throws ServiceException {
        Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );

		Clinica clinica = clinicaOp.get();
        return clinicaLogoSharedService.getLogo( clinica );
    }

    public Base64ImageResponse getPaginaInicialLogo( Long[] clinicasIDs ) throws ServiceException {
        if ( clinicasIDs == null )
            return clinicaLogoSharedService.getLogo( null );
        if ( clinicasIDs.length != 1 )
            return clinicaLogoSharedService.getLogo( null );
        
        Long clinicaId = clinicasIDs[ 0 ];
        
        Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );

		Clinica clinica = clinicaOp.get();        
        return clinicaLogoSharedService.getLogo( clinica );
    }

}
