import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepFormComponent } from './dep-form.component';

describe('DepFormComponent', () => {
  let component: DepFormComponent;
  let fixture: ComponentFixture<DepFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DepFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
