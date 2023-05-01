import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioFiltroComponent } from './usuario-filtro.component';

describe('UsuarioFiltroComponent', () => {
  let component: UsuarioFiltroComponent;
  let fixture: ComponentFixture<UsuarioFiltroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioFiltroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioFiltroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
