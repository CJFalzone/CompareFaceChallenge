import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagepageComponent } from './imagepage.component';

describe('ImagepageComponent', () => {
  let component: ImagepageComponent;
  let fixture: ComponentFixture<ImagepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImagepageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImagepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
