import { TestBed } from '@angular/core/testing';

import { FishingLessonReservationService } from './fishing-lesson-reservation.service';

describe('FishingLessonReservationService', () => {
  let service: FishingLessonReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FishingLessonReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
