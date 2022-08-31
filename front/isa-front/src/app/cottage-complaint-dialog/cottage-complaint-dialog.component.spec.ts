import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageComplaintDialogComponent } from './cottage-complaint-dialog.component';

describe('CottageComplaintDialogComponent', () => {
  let component: CottageComplaintDialogComponent;
  let fixture: ComponentFixture<CottageComplaintDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageComplaintDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageComplaintDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
