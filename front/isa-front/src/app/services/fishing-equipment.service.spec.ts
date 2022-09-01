import { TestBed } from '@angular/core/testing';

import { FishingEquipmentService } from './fishing-equipment.service';

describe('FishingEquipmentService', () => {
  let service: FishingEquipmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FishingEquipmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
