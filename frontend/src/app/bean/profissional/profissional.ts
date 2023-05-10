import { Usuario } from "../usuario/usuario";

export interface Profissional {
    id : number;
    nome : string;
    tempoConsulta: number;
    tempoConsultaRetorno: number;
    valorConsulta: number;
    funcao : string;
    funcaoLabel : string;
    usuario: Usuario;
}