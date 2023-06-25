import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatorioProntuarioComponent } from './relatorio-prontuario.component';

describe('RelatorioProntuarioComponent', () => {
  let component: RelatorioProntuarioComponent;
  let fixture: ComponentFixture<RelatorioProntuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RelatorioProntuarioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatorioProntuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
