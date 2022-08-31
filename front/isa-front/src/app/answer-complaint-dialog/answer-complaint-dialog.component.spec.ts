import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerComplaintDialogComponent } from './answer-complaint-dialog.component';

describe('AnswerComplaintDialogComponent', () => {
  let component: AnswerComplaintDialogComponent;
  let fixture: ComponentFixture<AnswerComplaintDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnswerComplaintDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerComplaintDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
