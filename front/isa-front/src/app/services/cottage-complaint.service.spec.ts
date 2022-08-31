import { TestBed } from '@angular/core/testing';

import { CottageComplaintService } from './cottage-complaint.service';

describe('CottageComplaintService', () => {
  let service: CottageComplaintService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageComplaintService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
