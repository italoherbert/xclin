import { TestBed } from '@angular/core/testing';

import { ExameVinculoService } from './exame-vinculo.service';

describe('ExameVinculoService', () => {
  let service: ExameVinculoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExameVinculoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
