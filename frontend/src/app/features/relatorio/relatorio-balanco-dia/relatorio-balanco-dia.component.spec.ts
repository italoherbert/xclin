import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatorioBalancoDiaComponent } from './relatorio-balanco-dia.component';

describe('RelatorioBalancoDiaComponent', () => {
  let component: RelatorioBalancoDiaComponent;
  let fixture: ComponentFixture<RelatorioBalancoDiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RelatorioBalancoDiaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatorioBalancoDiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
