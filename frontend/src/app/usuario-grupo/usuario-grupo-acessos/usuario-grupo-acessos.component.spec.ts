import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioGrupoAcessosComponent } from './usuario-grupo-acessos.component';

describe('UsuarioGrupoAcessosComponent', () => {
  let component: UsuarioGrupoAcessosComponent;
  let fixture: ComponentFixture<UsuarioGrupoAcessosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioGrupoAcessosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioGrupoAcessosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
