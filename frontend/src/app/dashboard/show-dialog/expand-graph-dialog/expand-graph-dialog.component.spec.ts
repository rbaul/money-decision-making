import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpandGraphDialogComponent } from './expand-graph-dialog.component';

describe('ExpandGraphDialogComponent', () => {
  let component: ExpandGraphDialogComponent;
  let fixture: ComponentFixture<ExpandGraphDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExpandGraphDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpandGraphDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
