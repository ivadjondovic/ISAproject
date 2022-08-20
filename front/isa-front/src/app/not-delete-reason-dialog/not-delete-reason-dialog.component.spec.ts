import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotDeleteReasonDialogComponent } from './not-delete-reason-dialog.component';

describe('NotDeleteReasonDialogComponent', () => {
  let component: NotDeleteReasonDialogComponent;
  let fixture: ComponentFixture<NotDeleteReasonDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotDeleteReasonDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotDeleteReasonDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
