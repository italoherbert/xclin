import { Municipio } from "../endereco/municipio";
import { Uf } from "../endereco/uf";
import { Paciente } from "./paciente";

export interface PacienteDetalhes {
    paciente : Paciente;
    uf : Uf;
    municipio: Municipio;
    sexo: any;
    estadoCivil: any;
    nacionalidade: any;
}