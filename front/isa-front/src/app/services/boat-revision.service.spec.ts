import { TestBed } from '@angular/core/testing';

import { BoatRevisionService } from './boat-revision.service';

describe('BoatRevisionService', () => {
  let service: BoatRevisionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatRevisionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
