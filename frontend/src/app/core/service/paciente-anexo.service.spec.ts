import { TestBed } from '@angular/core/testing';

import { PacienteAnexoService } from './paciente-anexo.service';

describe('PacienteAnexoService', () => {
  let service: PacienteAnexoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PacienteAnexoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
