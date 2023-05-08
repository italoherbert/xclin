import { Municipio } from "./municipio";
import { Uf } from "./uf";

export interface EnderecoSave {
    logradouro : string,
    numero : string;
    bairro : string;
    codigoMunicipio: number;
    codigoUf : number;
}