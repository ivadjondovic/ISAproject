import { TestBed } from '@angular/core/testing';

import { QuickCottageReservationService } from './quick-cottage-reservation.service';

describe('QuickCottageReservationService', () => {
  let service: QuickCottageReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuickCottageReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
