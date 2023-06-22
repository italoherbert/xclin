import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnamneseModeloDetalhesComponent } from './anamnese-modelo-detalhes.component';

describe('AnamneseModeloDetalhesComponent', () => {
  let component: AnamneseModeloDetalhesComponent;
  let fixture: ComponentFixture<AnamneseModeloDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnamneseModeloDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnamneseModeloDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
