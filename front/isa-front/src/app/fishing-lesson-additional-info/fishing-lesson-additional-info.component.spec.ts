import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingLessonAdditionalInfoComponent } from './fishing-lesson-additional-info.component';

describe('FishingLessonAdditionalInfoComponent', () => {
  let component: FishingLessonAdditionalInfoComponent;
  let fixture: ComponentFixture<FishingLessonAdditionalInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FishingLessonAdditionalInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FishingLessonAdditionalInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
