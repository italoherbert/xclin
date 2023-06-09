import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaNovaComponent } from './consulta-nova.component';

describe('ConsultaNovaComponent', () => {
  let component: ConsultaNovaComponent;
  let fixture: ComponentFixture<ConsultaNovaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaNovaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaNovaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
