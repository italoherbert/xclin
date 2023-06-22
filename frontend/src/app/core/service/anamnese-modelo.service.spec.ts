import { TestBed } from '@angular/core/testing';

import { AnamneseModeloService } from './anamnese-modelo.service';

describe('AnamneseModeloService', () => {
  let service: AnamneseModeloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnamneseModeloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
