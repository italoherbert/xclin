import { Municipio } from "./municipio";
import { Uf } from "./uf";

export interface Endereco {
    id : number;
    logradouro : string,
    numero : string;
    bairro : string;
    codigoMunicipio: number;
    codigoUf : number;
}