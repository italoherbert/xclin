import { Usuario } from "../usuario/usuario";

export interface Profissional {
    id : number;
    nome : string;
    funcao : string;
    funcaoLabel : string;
    usuario: Usuario;
}