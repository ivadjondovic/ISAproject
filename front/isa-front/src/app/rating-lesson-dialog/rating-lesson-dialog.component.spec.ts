import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatingLessonDialogComponent } from './rating-lesson-dialog.component';

describe('RatingLessonDialogComponent', () => {
  let component: RatingLessonDialogComponent;
  let fixture: ComponentFixture<RatingLessonDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatingLessonDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingLessonDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
