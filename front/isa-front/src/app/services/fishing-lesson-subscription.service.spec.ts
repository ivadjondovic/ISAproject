import { TestBed } from '@angular/core/testing';

import { FishingLessonSubscriptionService } from './fishing-lesson-subscription.service';

describe('FishingLessonSubscriptionService', () => {
  let service: FishingLessonSubscriptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FishingLessonSubscriptionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
