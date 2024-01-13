import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HeaderComponent } from "./header.component";
import { HttpClientModule } from "@angular/common/http"; // Import HttpClientModule
import { RouterTestingModule } from "@angular/router/testing"; // Import RouterTestingModule
import { SearchComponent } from "../search/search.component";
import { MaterialModule } from "../../../shared/module/material.module";
import { NO_ERRORS_SCHEMA } from "@angular/core";

describe("HeaderComponent", () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderComponent, SearchComponent],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [
        HttpClientModule,
        RouterTestingModule,
        MaterialModule,
      ],
    });
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should have a title 'Java UI Demo' by default", () => {
    expect(component.title).toEqual("Java UI Demo");
  });

  it("should call getCartItems() when the cart icon is clicked", () => {
    // Spy on the cartService's getCartItems method
    spyOn(component.cartService, "getCartItems");

    // Trigger the click event on the cart icon
    const cartIcon = fixture.nativeElement.querySelector(".cart-icon-container mat-icon");
    cartIcon.click();

    // Expect getCartItems to have been called
    expect(component.cartService.getCartItems).toHaveBeenCalled();
  });
});
