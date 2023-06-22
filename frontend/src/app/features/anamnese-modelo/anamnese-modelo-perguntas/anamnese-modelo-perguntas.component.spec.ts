import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnamneseModeloPerguntasComponent } from './anamnese-modelo-perguntas.component';

describe('AnamneseModeloPerguntasComponent', () => {
  let component: AnamneseModeloPerguntasComponent;
  let fixture: ComponentFixture<AnamneseModeloPerguntasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnamneseModeloPerguntasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnamneseModeloPerguntasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
