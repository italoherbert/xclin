package italo.xclin.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.logica.ImageUtil;
import italo.xclin.model.Clinica;
import italo.xclin.model.response.Base64ImageResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.service.shared.ClinicaLogoSharedService;

@Service
public class ClinicaLogoService {
    
    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private ClinicaLogoSharedService clinicaLogoSharedService;

    @Autowired
    private ImageUtil imageUtil;

    public void salvaLogo( Long clinicaId, MultipartFile logoFile ) throws ServiceException {
        Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );

		Clinica clinica = clinicaOp.get();
        try {            
            String base64Str = null;
            if ( logoFile != null ) {
                byte[] bytes = logoFile.getBytes();

                String contentType = logoFile.getContentType();
                base64Str = imageUtil.bytesToBase64( bytes, contentType );
            }            

            clinica.setLogomarca( base64Str );        
            clinicaRepository.save( clinica );
        } catch ( IOException e ) {
            throw new ServiceException( Erro.FALHA_LEITURA_LOGO_DEFAULT );
        }
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
