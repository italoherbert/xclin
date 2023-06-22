package italo.xclin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.loader.AnamneseModeloPerguntaLoader;
import italo.xclin.repository.AnamneseModeloPerguntaRepository;

@Service
public class AnamneseModeloPerguntaService {

	@Autowired
	private AnamneseModeloPerguntaRepository anamneseModeloPerguntaRepository;
	
	@Autowired
	private AnamneseModeloPerguntaLoader anamneseModeloPerguntaLoader;
		
}
