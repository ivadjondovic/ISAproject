import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRevisionsComponent } from './admin-revisions.component';

describe('AdminRevisionsComponent', () => {
  let component: AdminRevisionsComponent;
  let fixture: ComponentFixture<AdminRevisionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRevisionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRevisionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
