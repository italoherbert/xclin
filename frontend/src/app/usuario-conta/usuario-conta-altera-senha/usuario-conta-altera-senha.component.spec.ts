import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioContaAlteraSenhaComponent } from './usuario-conta-altera-senha.component';

describe('UsuarioContaAlteraSenhaComponent', () => {
  let component: UsuarioContaAlteraSenhaComponent;
  let fixture: ComponentFixture<UsuarioContaAlteraSenhaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsuarioContaAlteraSenhaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioContaAlteraSenhaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
