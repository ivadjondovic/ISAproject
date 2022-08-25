import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFishingLessonComponent } from './edit-fishing-lesson.component';

describe('EditFishingLessonComponent', () => {
  let component: EditFishingLessonComponent;
  let fixture: ComponentFixture<EditFishingLessonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditFishingLessonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditFishingLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
