import { TestBed } from '@angular/core/testing';

import { ClinicaExameService } from './clinica-exame.service';

describe('ClinicaExameService', () => {
  let service: ClinicaExameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClinicaExameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
