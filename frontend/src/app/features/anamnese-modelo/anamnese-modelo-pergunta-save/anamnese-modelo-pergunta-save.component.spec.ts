import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnamneseModeloPerguntaSaveComponent } from './anamnese-modelo-pergunta-save.component';

describe('AnamneseModeloPerguntaSaveComponent', () => {
  let component: AnamneseModeloPerguntaSaveComponent;
  let fixture: ComponentFixture<AnamneseModeloPerguntaSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnamneseModeloPerguntaSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnamneseModeloPerguntaSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
