import { TestBed } from '@angular/core/testing';

import { QuickBoatReservationService } from './quick-boat-reservation.service';

describe('QuickBoatReservationService', () => {
  let service: QuickBoatReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuickBoatReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
