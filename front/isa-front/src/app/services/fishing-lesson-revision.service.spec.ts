import { TestBed } from '@angular/core/testing';

import { FishingLessonRevisionService } from './fishing-lesson-revision.service';

describe('FishingLessonRevisionService', () => {
  let service: FishingLessonRevisionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FishingLessonRevisionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
