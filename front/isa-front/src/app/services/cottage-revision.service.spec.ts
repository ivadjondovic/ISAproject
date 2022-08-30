import { TestBed } from '@angular/core/testing';

import { CottageRevisionService } from './cottage-revision.service';

describe('CottageRevisionService', () => {
  let service: CottageRevisionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CottageRevisionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
