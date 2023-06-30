
export interface AtendimentoFiltro {
    dataInicio : string;
    dataFim : string;
    pacienteNomeIni : string;
    profissionalNomeIni : string;
    incluirPaciente : boolean;
    incluirProfissional : boolean;
    turno : string;
    status : string;
    incluirTodosTurnos : boolean;
    incluirTodosStatuses : boolean;
    incluirPagas : boolean;
    incluirRetornos : boolean;
}