import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClubhouseComponent } from './clubhouse.component';

describe('ClubhouseComponent', () => {
  let component: ClubhouseComponent;
  let fixture: ComponentFixture<ClubhouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClubhouseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClubhouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
