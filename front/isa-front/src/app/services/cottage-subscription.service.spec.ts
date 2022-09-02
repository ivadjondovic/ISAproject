import { TestBed } from '@angular/core/testing';

import { CottageSubscriptionService } from './cottage-subscription.service';

describe('CottageSubscriptionService', () => {
  let service: CottageSubscriptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageSubscriptionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
