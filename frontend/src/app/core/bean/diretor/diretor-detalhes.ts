
import { Usuario } from "../usuario/usuario";
import { Diretor } from "./diretor";

export interface DiretorDetalhes {
    diretor: Diretor;
    clinicas : string[];
}