import { Profissional } from "./profissional";

export interface ProfissionalDetalhes {
    profissional: Profissional;
    clinicas: string[];
    especialidades: string[];
}