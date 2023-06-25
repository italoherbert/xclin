import { AnamnesePergunta } from "./anamnese-pergunta";

export interface Anamnese {
    id : number;
    nome : string;
    dataCriacao : string;
    perguntas : AnamnesePergunta[];        
}