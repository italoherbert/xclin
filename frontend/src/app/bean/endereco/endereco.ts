import { Municipio } from "./municipio";
import { Uf } from "./uf";

export interface Endereco {
    id : number;
    logradouro : string,
    numero : string;
    bairro : string;
    municipio : Municipio;
    uf : Uf;
}