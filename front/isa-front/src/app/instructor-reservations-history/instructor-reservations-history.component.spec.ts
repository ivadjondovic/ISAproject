import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorReservationsHistoryComponent } from './instructor-reservations-history.component';

describe('InstructorReservationsHistoryComponent', () => {
  let component: InstructorReservationsHistoryComponent;
  let fixture: ComponentFixture<InstructorReservationsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorReservationsHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorReservationsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
