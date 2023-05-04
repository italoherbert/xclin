package italo.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.model.request.LoginRequest;
import italo.scm.model.response.LoginResponse;
import italo.scm.service.LoginService;
import italo.scm.validator.LoginValidator;

@RestController
@RequestMapping( "/api/login" )
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoginValidator loginValidator;
	
	@PostMapping("")
	public ResponseEntity<Object> login( @RequestBody LoginRequest request ) throws SistemaException {
		loginValidator.validaLogin( request );
		LoginResponse resp = loginService.login( request );
		return ResponseEntity.ok( resp );
	}
	
}
