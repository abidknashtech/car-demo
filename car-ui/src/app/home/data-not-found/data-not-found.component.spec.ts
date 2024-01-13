// import { ComponentFixture, TestBed } from "@angular/core/testing";
// import { DataNotFoundComponent } from "./data-not-found.component";
// import { HttpClientTestingModule } from "@angular/common/http/testing";
// import { RouterTestingModule } from "@angular/router/testing";
// import { MaterialModule } from "../../shared/module/material.module";
// import { HttpClientModule } from "@angular/common/http";
// describe("DataNotFoundComponent", () => {
//   let component: DataNotFoundComponent;
//   let fixture: ComponentFixture<DataNotFoundComponent>;
//
//   // Set up the testing module before each test
//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       declarations: [DataNotFoundComponent],
//       imports: [
//         HttpClientTestingModule,
//         HttpClientModule,
//         RouterTestingModule,
//         MaterialModule,
//       ],
//     });
//
//     // Create an instance of the component and its fixture
//     fixture = TestBed.createComponent(DataNotFoundComponent);
//     component = fixture.componentInstance;
//
//     // Trigger the initial data binding
//     fixture.detectChanges();
//   });
//
//   // Test case 1: Check if the component is created successfully
//   it("should create the DataNotFoundComponent", () => {
//     expect(component).toBeTruthy();
//   });
//
//   // Test case 2: Check if the HTML template is rendered correctly
//   it("should render the HTML template correctly", () => {
//     const compiled = fixture.nativeElement;
//
//     // Assert the presence of specific HTML elements and text content
//     expect(compiled.querySelector(".container")).toBeTruthy();
//     expect(compiled.querySelector(".oops").textContent).toContain("Oops !");
//   });
//
//   // Test case 3: Check if the "GENERATE DATA" button is clickable
//   it('should make the "GENERATE DATA" button clickable', () => {
//     const button = fixture.nativeElement.querySelector(".generate-button");
//
//     // Create a spy to track the function call or event emission
//     const generateDataSpy = spyOn(component, "generateData").and.callThrough();
//
//     // Trigger a click event on the button
//     button.click();
//
//     // Add assertions for the expected behavior after the click event
//     // For example, check if the function is called or an event is emitted
//     expect(generateDataSpy).toHaveBeenCalled();
//     // Add more assertions if needed
//   });
//
//   // Test case 5: Check if the component's styling is applied correctly
//   it("should apply styling as defined in the SCSS file", () => {
//     const compiled = fixture.nativeElement;
//
//     // Add assertions to check if the element has the expected class
//     expect(compiled.querySelector(".container")).toBeTruthy();
//   });
// // });
