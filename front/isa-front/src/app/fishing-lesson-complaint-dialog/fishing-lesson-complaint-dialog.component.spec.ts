import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingLessonComplaintDialogComponent } from './fishing-lesson-complaint-dialog.component';

describe('FishingLessonComplaintDialogComponent', () => {
  let component: FishingLessonComplaintDialogComponent;
  let fixture: ComponentFixture<FishingLessonComplaintDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FishingLessonComplaintDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FishingLessonComplaintDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
