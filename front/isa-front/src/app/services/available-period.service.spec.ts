import { TestBed } from '@angular/core/testing';

import { AvailablePeriodService } from './available-period.service';

describe('AvailablePeriodService', () => {
  let service: AvailablePeriodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AvailablePeriodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
