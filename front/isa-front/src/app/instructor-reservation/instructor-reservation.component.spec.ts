import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorReservationComponent } from './instructor-reservation.component';

describe('InstructorReservationComponent', () => {
  let component: InstructorReservationComponent;
  let fixture: ComponentFixture<InstructorReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
