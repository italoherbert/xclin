import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioGrupoTelaComponent } from './usuario-grupo-tela.component';

describe('UsuarioGrupoTelaComponent', () => {
  let component: UsuarioGrupoTelaComponent;
  let fixture: ComponentFixture<UsuarioGrupoTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioGrupoTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioGrupoTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
