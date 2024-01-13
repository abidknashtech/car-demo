import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ConfirmationDialogComponent } from "./confirmation-dialog.component";

describe("ConfirmationDialogComponent", () => {
  let component: ConfirmationDialogComponent;
  let fixture: ComponentFixture<ConfirmationDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConfirmationDialogComponent],
      providers: [
        // Mocking MAT_DIALOG_DATA with a sample CarsDataComponent object
        { provide: MAT_DIALOG_DATA, useValue: { selectedCloud: "MockCloud" } },
      ],
    });
    fixture = TestBed.createComponent(ConfirmationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should have the correct data passed to the dialog", () => {
    // Assuming you have a variable named 'selectedCloud' in CarsDataComponent
    expect(component.data.selectedCloud).toEqual("MockCloud");
  });
});
