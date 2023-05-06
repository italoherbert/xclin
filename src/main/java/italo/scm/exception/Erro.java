package italo.scm.exception;

public interface Erro {

	public final static String LOGIN_INVALIDO = "Usuário não encontrado por login e senha.";
	
	public final static String USERNAME_OBRIGATORIO = "O username é um campo de preenchimento obrigatório.";
	public final static String SENHA_OBRIGATORIA = "A senha é um campo de preenchimento obrigatório.";
	public final static String USUARIO_GRUPO_NOME_OBRIGATORIO = "O nome do grupo de usuário é um campo de preenchimento obrigatório.";
	
	public final static String USERNAME_INI_OBRIGATORIO = "O início do username é um campo de preenchimento obrigatório.";
	public final static String USUARIO_GRUPO_NOME_INI_OBRIGATORIO = "O início do nome do grupo de usuário é um campo de preenchimento obrigatório.";

	public final static String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";
	public final static String USUARIO_GRUPO_NAO_ENCONTRADO = "Grupo não encontrado.";
	public final static String RECURSO_NAO_ENCONTRADO = "Recurso não encontrado.";
	public final static String ACESSO_RECURSO_NAO_ENCONTRADO = "Não foi possível encontrar o recurso pelo ID: $1";
	public final static String VINCULO_USUARIO_GRUPO_NAO_ENCONTRADO = "Não foi possível encontrar o grupo de usuário pelo ID: $1";
	
	public final static String USUARIO_JA_EXISTE = "Já existe um outro usuário com o username informado.";
	public final static String USUARIO_GRUPO_JA_EXISTE = "Já existe um outro grupo de usuário com o nome informado.";
	public final static String RECURSO_JA_EXISTE = "Já existe um recurso com o nome informado.";
	
	public final static String USERNAME_NAO_DISPONIVEL = "Já existe um usuário com username: $1.";
	public final static String USUARIO_GRUPO_NOME_NAO_DISPONIVEL = "Já existe um grupo de usuário com username: $1.";
	
	public final static String PERFIL_INVALIDO = "Perfil inválido: $1";
	
	public final static String STRNUM_INVALIDO = "Não foi possível converter o texto: '$1' em número.";
	public final static String STRDATA_INVALIDO = "Não foi possível converter o texto: '$1' em data.";
}
