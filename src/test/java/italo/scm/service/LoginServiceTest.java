package italo.scm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import italo.scm.enums.tipos.UsuarioPerfil;
import italo.scm.logica.HashUtil;
import italo.scm.logica.jwt.JWTLogica;
import italo.scm.model.Usuario;
import italo.scm.model.request.LoginRequest;
import italo.scm.model.response.LoginResponse;
import italo.scm.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

	@InjectMocks
	private LoginService loginService;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private JWTLogica jwtLogica;
	
	@Mock
	private HashUtil hashUtil;
		
	@Test
	void loginTest() throws Exception {	
		String username = "italo";
		String token = "AFE83CADB784972";
		String senha = "italo";
		String hash = "A385740FCA749D";
		
		List<String> roles = new ArrayList<>();
		roles.add( "usuarioREAD" );
		roles.add( "usuarioWRITE" );
		
		LoginRequest request = new LoginRequest();
		request.setUsername( username );
		request.setSenha( senha ); 
		
		Usuario u = Mockito.mock( Usuario.class ); 
		u.setId( 1L ); 
		u.setPerfil( UsuarioPerfil.ADMIN );
		u.setUsername( username );
		u.setSenha( senha );
				
		Optional<Usuario> usuarioOp= Optional.of( u );
		
		Mockito.when( jwtLogica.geraToken( Mockito.anyString(), Mockito.anyList() ) ).thenReturn( token );
		Mockito.when( hashUtil.geraHash( Mockito.anyString() ) ).thenReturn( hash );
		Mockito.when( usuarioRepository.buscaPorLogin( Mockito.anyString(), Mockito.anyString() ) ).thenReturn( usuarioOp );
		
		LoginResponse resp = loginService.login( request );
		assertEquals( username, resp.getUsername() );
		assertEquals( token, resp.getToken() );
		assertEquals( u.getPerfil(), resp.getPerfil() );
	}
	
}
