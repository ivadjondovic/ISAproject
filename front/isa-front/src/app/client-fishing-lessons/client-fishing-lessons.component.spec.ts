import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientFishingLessonsComponent } from './client-fishing-lessons.component';

describe('ClientFishingLessonsComponent', () => {
  let component: ClientFishingLessonsComponent;
  let fixture: ComponentFixture<ClientFishingLessonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientFishingLessonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientFishingLessonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
