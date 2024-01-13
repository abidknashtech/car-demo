import { ComponentFixture, TestBed } from "@angular/core/testing";
import { DataNotFoundComponent } from "./data-not-found.component";
import { HttpClientModule } from "@angular/common/http";
import { RouterTestingModule } from "@angular/router/testing";
import { MaterialModule } from "../../module/material.module";

describe("DataNotFoundComponent", () => {
  let component: DataNotFoundComponent;
  let fixture: ComponentFixture<DataNotFoundComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DataNotFoundComponent],
      imports: [HttpClientModule, RouterTestingModule, MaterialModule],
    });
    fixture = TestBed.createComponent(DataNotFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should have the correct HTML elements", () => {
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector(".oops").textContent).toContain("Oops !");
    expect(compiled.querySelector(".messages").textContent).toContain(
      "DATA NOT FOUND",
    );
    expect(compiled.querySelector(".message").textContent).toContain(
      "The data you have been searching for has not been generated",
    );
    expect(compiled.querySelector(".generate").textContent).toContain(
      "Click on the below button to generate the Data.",
    );
    expect(compiled.querySelector(".generate-button").textContent).toContain(
      "ADD BULK DATA",
    );
  });

  it("should call generateData method on button click", () => {
    spyOn(component, "generateData");
    const button = fixture.nativeElement.querySelector(".generate-button");
    button.click();
    expect(component.generateData).toHaveBeenCalled();
  });
});
