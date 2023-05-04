import { TestBed } from '@angular/core/testing';

import { UsuarioGrupoService } from './usuario-grupo.service';

describe('UsuarioGrupoService', () => {
  let service: UsuarioGrupoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsuarioGrupoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
