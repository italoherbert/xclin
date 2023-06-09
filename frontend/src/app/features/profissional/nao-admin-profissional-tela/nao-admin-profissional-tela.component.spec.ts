import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaoAdminProfissionalTelaComponent } from './nao-admin-profissional-tela.component';

describe('NaoAdminProfissionalTelaComponent', () => {
  let component: NaoAdminProfissionalTelaComponent;
  let fixture: ComponentFixture<NaoAdminProfissionalTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NaoAdminProfissionalTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NaoAdminProfissionalTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
