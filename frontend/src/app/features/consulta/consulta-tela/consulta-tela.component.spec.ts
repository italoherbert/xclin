import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaTelaComponent } from './consulta-tela.component';

describe('ConsultaTelaComponent', () => {
  let component: ConsultaTelaComponent;
  let fixture: ComponentFixture<ConsultaTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
