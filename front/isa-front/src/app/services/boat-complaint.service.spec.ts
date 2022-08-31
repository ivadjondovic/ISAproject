import { TestBed } from '@angular/core/testing';

import { BoatComplaintService } from './boat-complaint.service';

describe('BoatComplaintService', () => {
  let service: BoatComplaintService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatComplaintService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
