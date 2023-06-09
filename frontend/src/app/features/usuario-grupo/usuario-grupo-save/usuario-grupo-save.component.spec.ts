import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioGrupoSaveComponent } from './usuario-grupo-save.component';

describe('UsuarioGrupoSaveComponent', () => {
  let component: UsuarioGrupoSaveComponent;
  let fixture: ComponentFixture<UsuarioGrupoSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioGrupoSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioGrupoSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
