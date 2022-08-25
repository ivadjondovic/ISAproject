import { TestBed } from '@angular/core/testing';

import { FishingLessonService } from './fishing-lesson.service';

describe('FishingLessonService', () => {
  let service: FishingLessonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FishingLessonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
