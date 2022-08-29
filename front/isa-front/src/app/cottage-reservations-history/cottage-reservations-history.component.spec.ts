import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageReservationsHistoryComponent } from './cottage-reservations-history.component';

describe('CottageReservationsHistoryComponent', () => {
  let component: CottageReservationsHistoryComponent;
  let fixture: ComponentFixture<CottageReservationsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageReservationsHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageReservationsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
