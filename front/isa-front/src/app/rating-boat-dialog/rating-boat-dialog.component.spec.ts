import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatingBoatDialogComponent } from './rating-boat-dialog.component';

describe('RatingBoatDialogComponent', () => {
  let component: RatingBoatDialogComponent;
  let fixture: ComponentFixture<RatingBoatDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatingBoatDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingBoatDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
