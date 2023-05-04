import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioGrupoDetalhesComponent } from './usuario-grupo-detalhes.component';

describe('UsuarioGrupoDetalhesComponent', () => {
  let component: UsuarioGrupoDetalhesComponent;
  let fixture: ComponentFixture<UsuarioGrupoDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioGrupoDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioGrupoDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
