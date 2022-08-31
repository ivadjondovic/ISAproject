import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatingCottageDialogComponent } from './rating-cottage-dialog.component';

describe('RatingCottageDialogComponent', () => {
  let component: RatingCottageDialogComponent;
  let fixture: ComponentFixture<RatingCottageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatingCottageDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingCottageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
