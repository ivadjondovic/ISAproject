import { TestBed } from '@angular/core/testing';

import { FishingLessonComplaintService } from './fishing-lesson-complaint.service';

describe('FishingLessonComplaintService', () => {
  let service: FishingLessonComplaintService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FishingLessonComplaintService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
