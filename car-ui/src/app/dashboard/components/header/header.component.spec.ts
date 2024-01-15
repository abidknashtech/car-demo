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
  // Initializes 'isCartUIVisible' to false by default.
  it('should initialize isCartUIVisible to false by default', () => {
    expect(component.isCartUIVisible).toBe(false);
  });

  // 'isCartUIVisible' is already true before 'ngOnInit' is called.
  it('should not change isCartUIVisible if it is already true before ngOnInit is called', () => {
    component.isCartUIVisible = true;
    component.ngOnInit();
    expect(component.isCartUIVisible).toBe(true);
  });

  // 'ngOnInit' is called multiple times.
  it('should not change isCartUIVisible if ngOnInit is called multiple times', () => {
    component.ngOnInit();
    component.isCartUIVisible = true;
    component.ngOnInit();
    expect(component.isCartUIVisible).toBe(true);
  });

  // Sets 'isCartUIVisible' to true if the current route includes "cart"
  it('should set isCartUIVisible to true when current route includes "cart"', () => {
    spyOnProperty(component.router, 'url', 'get').and.returnValue("/cart");

    component.ngOnInit();

    expect(component.isCartUIVisible).toBe(true);
  });
});
