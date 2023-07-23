import { TestBed } from '@angular/core/testing';

import { ContaSuporteService } from './conta-suporte.service';

describe('ContaSuporteService', () => {
  let service: ContaSuporteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContaSuporteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
