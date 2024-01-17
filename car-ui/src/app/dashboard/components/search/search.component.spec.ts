import { ComponentFixture, TestBed } from "@angular/core/testing";
import { SearchComponent } from "./search.component";
import { HttpClientModule } from "@angular/common/http";
import { CartService } from "../../../shared/services/cart.service";
import { RouterTestingModule } from "@angular/router/testing";
import { MaterialModule } from "../../../shared/module/material.module";
import { NO_ERRORS_SCHEMA } from "@angular/core";

describe("SearchComponent", () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchComponent],
      imports: [HttpClientModule, RouterTestingModule, MaterialModule],
      providers: [CartService],
      schemas: [NO_ERRORS_SCHEMA],
    });
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should initialize with default values", () => {
    // Assert that the component has default values after initialization
    expect(component.items).toEqual(["All", "Brand", "Price", "Mileage", "Id"]);
    expect(component.filteredItems).toEqual([
      "Brand",
      "Price",
      "Mileage",
      "Id",
    ]);
    expect(component.searchTerm).toEqual("");
    expect(component.selectedCategory).toEqual("All");
  });

  it("should update selectedCategory and filter items on menuItemClicked", () => {
    // Arrange
    const selectedItem = "Brand";

    // Act
    component.menuItemClicked(selectedItem);

    // Assert
    expect(component.selectedCategory).toEqual(selectedItem);
    expect(component.filteredItems).toEqual(["All", "Price", "Mileage", "Id"]);
  });

  it("should navigate to search result page on searchButtonClicked", () => {
    // Arrange
    spyOn(component.router, 'navigate');

    // Act
    component.searchButtonClicked();

    // Assert
    expect(component.router.navigate).toHaveBeenCalledWith(['/search-result'], {
      queryParams: {
        category: component.selectedCategory,
        term: component.searchTerm,
      },
    });
  });

  // Logs the search term to the console when called with valid input.
  it('should log the search term to the console when called with valid input', () => {
    spyOn(console, 'log');
    component.searchTerm = 'valid input';
    component.filterItems();
    expect(console.log).toHaveBeenCalledWith('Search for :  ', 'valid input');
  });

  // Does not throw any errors or exceptions when called with valid input.
  it('should not throw any errors or exceptions when called with valid input', () => {
    expect(() => {
      component.searchTerm = 'valid input';
      component.filterItems();
    }).not.toThrow();
  });
});
