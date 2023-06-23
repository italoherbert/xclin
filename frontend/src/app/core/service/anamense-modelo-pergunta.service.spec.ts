import { TestBed } from '@angular/core/testing';

import { AnamenseModeloPerguntaService } from './anamense-modelo-pergunta.service';

describe('AnamenseModeloPerguntaService', () => {
  let service: AnamenseModeloPerguntaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnamenseModeloPerguntaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
