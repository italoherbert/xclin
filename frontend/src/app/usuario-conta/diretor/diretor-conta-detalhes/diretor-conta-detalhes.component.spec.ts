import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretorContaDetalhesComponent } from './diretor-conta-detalhes.component';

describe('DiretorContaDetalhesComponent', () => {
  let component: DiretorContaDetalhesComponent;
  let fixture: ComponentFixture<DiretorContaDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiretorContaDetalhesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiretorContaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
