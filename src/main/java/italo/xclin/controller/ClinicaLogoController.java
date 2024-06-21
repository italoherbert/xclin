package italo.xclin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.save.ClinicaLogoSaveRequest;
import italo.xclin.model.response.Base64ImageResponse;
import italo.xclin.service.ClinicaLogoService;
import italo.xclin.service.autorizador.Autorizador;

@RestController
@RequestMapping("/api/clinica/logo")
public class ClinicaLogoController {
    
    @Autowired
    private ClinicaLogoService clinicaLogoService;

    @Autowired
    private Autorizador autorizador;

    @Autowired
    private JWTTokenLogica jwtTokenLogica;

    @PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/{clinicaId}")
	public Base64ImageResponse getLogo( 
            @RequestHeader( "Authorization" ) String authorizationHeader,     
            @PathVariable Long clinicaId ) throws SistemaException {

        autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		return clinicaLogoService.getLogo( clinicaId );
	}

    @PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/initial-page")
    public Base64ImageResponse getPaginaInicialLogo(
            @RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
        JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
        Long[] clinicasIDs = tokenInfo.getClinicasIDs();

        return clinicaLogoService.getPaginaInicialLogo( clinicasIDs );
    }

    @PreAuthorize("hasAuthority('clinicaWRITE')")
	@PutMapping("/{clinicaId}")
	public ResponseEntity<Object> alteraLogo( 
            @RequestHeader( "Authorization" ) String authorizationHeader,     
            @PathVariable Long clinicaId,
            @RequestBody ClinicaLogoSaveRequest request ) throws SistemaException {

        autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
        clinicaLogoService.salvaLogo( clinicaId, request );
		return ResponseEntity.ok().build();
	}

    @PreAuthorize("hasAuthority('clinicaWRITE')")
	@PutMapping("/to-default/{clinicaId}")
	public ResponseEntity<Object> alteraParaLogoDefault( 
            @RequestHeader( "Authorization" ) String authorizationHeader,     
            @PathVariable Long clinicaId ) throws SistemaException {

        ClinicaLogoSaveRequest request = new ClinicaLogoSaveRequest();
        request.setLogo( null );

        autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
        clinicaLogoService.salvaLogo( clinicaId, request );
		return ResponseEntity.ok().build();
	}

}
