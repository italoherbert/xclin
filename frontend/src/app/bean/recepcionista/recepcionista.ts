import { Usuario } from "../usuario/usuario";

export interface Recepcionista {
    id : number;
    nome : string;
    clinicaId : number;
    clinicaNome : string;
    usuario : Usuario;
}