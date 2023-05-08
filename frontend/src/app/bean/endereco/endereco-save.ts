import { Municipio } from "./municipio";
import { Uf } from "./uf";

export interface EnderecoSave {
    logradouro : string,
    numero : string;
    bairro : string;
    municipio : Municipio;
    uf : Uf;
}