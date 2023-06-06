package italo.xclin.exception;

public interface Erro {

	public final static String LOGIN_INVALIDO = "Usuário não encontrado por login e senha.";
	public final static String CLINICA_ACESSO_NAO_AUTORIZADO = "Seu usuário não tem vínculo com a clinica.";
	public final static String PROFISSIONAL_ACESSO_NAO_AUTORIZADO = "Seu usuário não é o profissional com permissão para realizar essa função.";
	
	public final static String NAO_PODE_INICIAR_CONSULTA = "Você não pode iniciar a consulta por este formulário.";
	public final static String USUARIO_NAO_DELETADO_POR_PERFIL = "Não é possível deletar um usuário com perfil diferente de RAIZ e ADMIN por aqui.";
	
	public final static String USERNAME_OBRIGATORIO = "O username é um campo de preenchimento obrigatório.";
	public final static String SENHA_OBRIGATORIA = "A senha é um campo de preenchimento obrigatório.";
	public final static String USUARIO_GRUPO_NOME_OBRIGATORIO = "O nome do grupo de usuário é um campo de preenchimento obrigatório.";
	public final static String NOME_OBRIGATORIO = "O nome é um campo de preenchimento obrigatório.";
	public final static String TELEFONE_OBRIGATORIO = "O telefone é um campo de preenchimento obrigatório.";
	public final static String TURNO_OBRIGATORIO = "O turno é um campo de preenchimento obrigatório.";
	public final static String STATUS_OBRIGATORIO = "O status é um campo de preenchimento obrigatório.";
	public final static String DATA_OBRIGATORIA = "A data é um campo de preenchimento obrigatório.";
	
	public final static String USERNAME_INI_OBRIGATORIO = "O início do username é um campo de preenchimento obrigatório.";
	public final static String NOME_INI_OBRIGATORIO = "O início do nome é um campo de preenchimento obrigatório.";
	public final static String CLINICA_NOME_INI_OBRIGATORIO = "O início do nome da clínica é um campo de preenchimento obrigatório.";
	public final static String PACIENTE_NOME_INI_OBRIGATORIO = "O início do nome do paciente é um campo de preenchimento obrigatório.";
	public final static String PROFISSIONAL_NOME_INI_OBRIGATORIO = "O início do nome do profissional é um campo de preenchimento obrigatório.";
	public final static String DATA_INI_OBRIGATORIA = "A data de início é um campo de preenchimento obrigatório.";
	public final static String DATA_FIM_OBRIGATORIA = "A data de fim é um campo de preenchimento obrigatório.";
		
	public final static String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";
	public final static String USUARIO_GRUPO_NAO_ENCONTRADO = "Grupo não encontrado.";
	public final static String RECURSO_NAO_ENCONTRADO = "Recurso não encontrado.";
	public final static String ACESSO_RECURSO_NAO_ENCONTRADO = "Não foi possível encontrar o recurso pelo ID: $1";
	public final static String VINCULO_USUARIO_GRUPO_NAO_ENCONTRADO = "Não foi possível encontrar o grupo de usuário pelo ID: $1";
	public final static String CLINICA_NAO_ENCONTRADA = "Clínica não encontrada.";
	public final static String MUNICIPIO_NAO_ENCONTRADO = "Município não encontrado.";
	public final static String UF_NAO_ENCONTRADA = "Unidade Federetiva não encontrada.";
	public final static String DIRETOR_NAO_ENCONTRADO = "Diretor não encontrado.";
	public final static String PROFISSIONAL_NAO_ENCONTRADO = "Profissional não encontrado.";
	public final static String RECEPCIONISTA_NAO_ENCONTRADO = "Recepcionista não encontrado(a).";
	public final static String PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado.";
	public final static String CLINICA_PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado na clínica informada.";
	public final static String CONSULTA_NAO_ENCONTRADA = "Consulta não encontrada.";
	public final static String ESPECIALIDADE_NAO_ENCONTRADA = "Especialidade não encontrada.";
	public final static String VINCULO_PROFISSIONAL_ESPECIALIDADE_NAO_ENCONTRADO = "Não foi encontrada a especialidade para adicioná-la ao profissional.";
	public final static String ANAMNESE_NAO_ENCONTRADA = "Anamnese do paciente não encontrada.";
		
	public final static String USUARIO_LOGADO_NAO_ENCONTRADO = "O usuário logado não foi encontrado pelo ID.";
	
	public final static String ANAMNESE_NAO_PREENCHIDA = "A anamnese do paciente ainda não foi preenchida.";

	public final static String USUARIO_JA_EXISTE = "Já existe um outro usuário com o username informado.";
	public final static String USUARIO_GRUPO_JA_EXISTE = "Já existe um outro grupo de usuário com o nome informado.";
	public final static String RECURSO_JA_EXISTE = "Já existe um recurso com o nome informado.";
	public final static String CLINICA_JA_EXISTE = "Já existe uma clínica registrada com o nome informado.";
	public final static String DIRETOR_JA_EXISTE = "Já existe um diretor registrado com o nome informado.";
	public final static String PROFISSIONAL_JA_EXISTE = "Já existe um profissional registrado com o nome informado.";
	public final static String RECEPCIONISTA_JA_EXISTE = "Já existe um(a) recepcionista registrado(a) com o nome informado.";
	public final static String PACIENTE_JA_EXISTE = "Já existe um(a) paciente registrado(a) com o nome informado";
	public final static String CLINICA_PACIENTE_JA_EXISTE = "Já existe um(a) paciente registrado(a) com o nome informado na clínica informada.";
	public final static String ESPECIALIDADE_JA_EXISTE = "Já existe uma especialidade com o nome informado.";

	public final static String USERNAME_NAO_DISPONIVEL = "Já existe um usuário com username: $1.";
	
	public final static String ENDERECO_NULL = "Objeto endereço não informado.";
	public final static String USUARIO_NULL = "Objeto usuário não informado.";
	public final static String OBSERVACOES_NULL = "Observações null.";
	public final static String PRESSAO_ARTERIAL_NULA = "Pressão arterial nula.";
	public final static String CICATRIZACAO_NULA = "Cicatrização nula.";
	public final static String TIPO_SANGRAMENTO_NULO = "Tipo de sangramento nulo.";
	public final static String DATA_ULTIMO_TRATAMENTO_NULA = "A data de último tratamento é nula.";
	
	public final static String PERFIL_INVALIDO = "Perfil inválido: $1";
	public final static String CONSULTA_STATUS_INVALIDO = "Status da consulta inválido: $1";
	public final static String TURNO_INVALIDO = "Turno inválido: $1";
	public final static String PRESSAO_ARTERIAL_INVALIDA = "Pressão arterial inválida: $1";
	public final static String CICATRIZACAO_INVALIDA = "Cicatrização inválida: $1";
	public final static String TIPO_SANGRAMENTO_INVALIDO = "Tipo de sangramento inválido: $1";
	
	public final static String DATA_NASCIMENTO_INVALIDA = "A data de nascimento está em formato inválido.";
	public final static String DATA_ULTIMO_TRATAMENTO_INVALIDA = "A data de última tratamento está em formato inválido.";
	public final static String DATA_ATENDIMENTO_INVALIDA = "A data de atendimento está em formato inválido.";
	public final static String DATA_INI_INVALIDA = "A data de início está em formato inválido.";
	public final static String DATA_FIM_INVALIDA = "A data de fim está em formato inválido.";
	public final static String DATA_CONSULTA_FILA_INVALIDA = "A data do dia da fila está em formato inválido.";
	
	public final static String STRNUM_INVALIDO = "Não foi possível converter o texto: '$1' em número.";
	public final static String STRDATA_INVALIDO = "Não foi possível converter o texto: '$1' em data.";
	
	public final static String PACIENTE_CLINICA_NAO_CORRESPONDEM = "O paciente não corresponde a clínica informada.";
	
	public final static String FALHA_GER_ANAMNESE_PDF = "Falha na geração do relatório de anamnese.";
	
	public final static String FALHA_LEITURA_IMG_DE_RELATORIO = "A imagem: $1 do relatório não foi encontrada no classpath.";
}
