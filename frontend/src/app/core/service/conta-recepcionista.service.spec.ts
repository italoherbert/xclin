import { TestBed } from '@angular/core/testing';

import { ContaRecepcionistaService } from './conta-recepcionista.service';

describe('ContaRecepcionistaService', () => {
  let service: ContaRecepcionistaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContaRecepcionistaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
