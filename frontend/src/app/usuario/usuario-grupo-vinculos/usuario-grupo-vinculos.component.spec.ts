import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioGrupoVinculosComponent } from './usuario-grupo-vinculos.component';

describe('UsuarioGrupoVinculosComponent', () => {
  let component: UsuarioGrupoVinculosComponent;
  let fixture: ComponentFixture<UsuarioGrupoVinculosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioGrupoVinculosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioGrupoVinculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
