import { TestBed } from '@angular/core/testing';

import { ProcedimentoService } from './procedimento.service';

describe('ProcedimentoService', () => {
  let service: ProcedimentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProcedimentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
