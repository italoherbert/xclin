import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioGrupoVinculosSaveComponent } from './usuario-grupo-vinculos-save.component';

describe('UsuarioGrupoVinculosSaveComponent', () => {
  let component: UsuarioGrupoVinculosSaveComponent;
  let fixture: ComponentFixture<UsuarioGrupoVinculosSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioGrupoVinculosSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioGrupoVinculosSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
