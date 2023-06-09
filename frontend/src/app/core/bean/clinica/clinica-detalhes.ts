import { Municipio } from "../endereco/municipio";
import { Uf } from "../endereco/uf";
import { Clinica } from "./clinica";

export interface ClinicaDetalhes {
    clinica : Clinica;
    uf : Uf;
    municipio : Municipio;
}