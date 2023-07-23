import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuporteContaDetalhesComponent } from './suporte-conta-detalhes.component';

describe('SuporteContaDetalhesComponent', () => {
  let component: SuporteContaDetalhesComponent;
  let fixture: ComponentFixture<SuporteContaDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuporteContaDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuporteContaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
