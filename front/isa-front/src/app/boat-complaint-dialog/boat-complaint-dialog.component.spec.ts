import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatComplaintDialogComponent } from './boat-complaint-dialog.component';

describe('BoatComplaintDialogComponent', () => {
  let component: BoatComplaintDialogComponent;
  let fixture: ComponentFixture<BoatComplaintDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatComplaintDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatComplaintDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
