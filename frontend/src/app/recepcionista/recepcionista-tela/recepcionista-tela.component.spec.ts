import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecepcionistaTelaComponent } from './recepcionista-tela.component';

describe('RecepcionistaTelaComponent', () => {
  let component: RecepcionistaTelaComponent;
  let fixture: ComponentFixture<RecepcionistaTelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecepcionistaTelaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecepcionistaTelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
