import { TestBed } from '@angular/core/testing';

import { ContaDiretorService } from './conta-diretor.service';

describe('ContaDiretorService', () => {
  let service: ContaDiretorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContaDiretorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
