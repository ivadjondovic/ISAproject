import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPenaltiesComponent } from './admin-penalties.component';

describe('AdminPenaltiesComponent', () => {
  let component: AdminPenaltiesComponent;
  let fixture: ComponentFixture<AdminPenaltiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPenaltiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPenaltiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
