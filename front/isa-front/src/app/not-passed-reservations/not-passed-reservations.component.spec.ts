import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotPassedReservationsComponent } from './not-passed-reservations.component';

describe('NotPassedReservationsComponent', () => {
  let component: NotPassedReservationsComponent;
  let fixture: ComponentFixture<NotPassedReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotPassedReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotPassedReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
