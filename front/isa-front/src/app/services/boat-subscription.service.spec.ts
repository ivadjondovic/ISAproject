import { TestBed } from '@angular/core/testing';

import { BoatSubscriptionService } from './boat-subscription.service';

describe('BoatSubscriptionService', () => {
  let service: BoatSubscriptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatSubscriptionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
