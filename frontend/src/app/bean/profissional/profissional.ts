import { Usuario } from "../usuario/usuario";

export interface Profissional {
    id : number;
    nome : string;
    valorConsulta: number;
    funcao : string;
    funcaoLabel : string;
    usuario: Usuario;
}