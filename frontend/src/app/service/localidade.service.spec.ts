import { TestBed } from '@angular/core/testing';

import { LocalidadeService } from './localidade.service';

describe('LocalidadeService', () => {
  let service: LocalidadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalidadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
