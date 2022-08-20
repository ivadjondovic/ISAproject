import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageAdditionalInfoComponent } from './cottage-additional-info.component';

describe('CottageAdditionalInfoComponent', () => {
  let component: CottageAdditionalInfoComponent;
  let fixture: ComponentFixture<CottageAdditionalInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageAdditionalInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageAdditionalInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
