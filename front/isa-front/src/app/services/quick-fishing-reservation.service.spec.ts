import { TestBed } from '@angular/core/testing';

import { QuickFishingReservationService } from './quick-fishing-reservation.service';

describe('QuickFishingReservationService', () => {
  let service: QuickFishingReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuickFishingReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
