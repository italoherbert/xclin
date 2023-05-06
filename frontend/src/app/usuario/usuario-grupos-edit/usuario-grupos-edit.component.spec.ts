import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioGruposEditComponent } from './usuario-grupos-edit.component';

describe('UsuarioGruposEditComponent', () => {
  let component: UsuarioGruposEditComponent;
  let fixture: ComponentFixture<UsuarioGruposEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioGruposEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioGruposEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
