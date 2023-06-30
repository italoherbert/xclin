import { TestBed } from '@angular/core/testing';

import { ContaProfissionalService } from './conta-profissional.service';

describe('ContaProfissionalService', () => {
  let service: ContaProfissionalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContaProfissionalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
