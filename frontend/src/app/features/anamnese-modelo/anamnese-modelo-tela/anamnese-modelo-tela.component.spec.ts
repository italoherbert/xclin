import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnamneseModeloTelaComponent } from './anamnese-modelo-tela.component';

describe('AnamneseModeloTelaComponent', () => {
  let component: AnamneseModeloTelaComponent;
  let fixture: ComponentFixture<AnamneseModeloTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnamneseModeloTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnamneseModeloTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
