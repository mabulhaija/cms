import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepListComponent } from './dep-list.component';

describe('ListComponent', () => {
  let component: DepListComponent;
  let fixture: ComponentFixture<DepListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DepListComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DepListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
