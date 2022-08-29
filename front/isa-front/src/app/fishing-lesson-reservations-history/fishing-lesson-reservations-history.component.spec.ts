import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingLessonReservationsHistoryComponent } from './fishing-lesson-reservations-history.component';

describe('FishingLessonReservationsHistoryComponent', () => {
  let component: FishingLessonReservationsHistoryComponent;
  let fixture: ComponentFixture<FishingLessonReservationsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FishingLessonReservationsHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FishingLessonReservationsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
