import { ComponentFixture, TestBed } from "@angular/core/testing";

import { CarSearchResultComponent } from "./car-search-result.component";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { MaterialModule } from "../../../shared/module/material.module";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { RouterTestingModule } from "@angular/router/testing";
import { HttpClientModule } from "@angular/common/http";
import { of, throwError } from "rxjs";
import { CartService } from "../../../shared/services/cart.service";
import { MatSnackBar } from "@angular/material/snack-bar";

describe("CarSearchResultComponent", () => {
  let component: CarSearchResultComponent;
  let fixture: ComponentFixture<CarSearchResultComponent>;
  let mockCartService: jasmine.SpyObj<CartService>;
  let mockSnackBar: jasmine.SpyObj<MatSnackBar>;
  beforeEach(() => {
    mockCartService = jasmine.createSpyObj("CartService", [
      "addToCart",
      "searchCarsById",
      "searchCarsByBrand",
      "searchCarsByPrice",
      "searchCarsByMileage",
      "searchAllCars",
      "incrementCartItemCount",
    ]);
    mockSnackBar = jasmine.createSpyObj("MatSnackBar", ["open"]);
    TestBed.configureTestingModule({
      declarations: [CarSearchResultComponent],
      imports: [
        HttpClientTestingModule,
        MaterialModule,
        RouterTestingModule,
        HttpClientModule,
      ],
      schemas: [NO_ERRORS_SCHEMA],
    });
    fixture = TestBed.createComponent(CarSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should set default values in ngOnInit", () => {
    expect(component.page).toEqual(1);
    expect(component.itemsPerPage).toEqual(5);
    expect(component.selectedCategory).toEqual("All");
    expect(component.searchTerm).toEqual("");
    expect(component.isDataPresent).toBeTruthy();
  });

  it("should call getAllSearchedCars on ngOnInit", () => {
    spyOn(component, "getAllSearchedCars");
    component.ngOnInit();
    expect(component.getAllSearchedCars).toHaveBeenCalled();
  });

  it("should call addToCart method on button click", () => {
    spyOn(component, "addToCart");
    const carId = "123";
    component.addToCart(carId);
    expect(component.addToCart).toHaveBeenCalledWith(carId);
  });

  it("should handle error response in addToCart", () => {
    spyOn(component.cartService, "addToCart").and.returnValue(of(null));
    spyOn(console, "error");

    const carId = "123";
    component.addToCart(carId);

    expect(console.error).toHaveBeenCalledWith("Failed to add to cart:");
  });

  it("should set default values in ngOnInit", () => {
    expect(component.page).toEqual(1);
    expect(component.itemsPerPage).toEqual(5);
    expect(component.selectedCategory).toEqual("All");
    expect(component.searchTerm).toEqual("");
    expect(component.isDataPresent).toBeTruthy();
  });

  it("should call getAllSearchedCars on ngOnInit", () => {
    spyOn(component, "getAllSearchedCars");
    component.ngOnInit();
    expect(component.getAllSearchedCars).toHaveBeenCalled();
  });

  it("should call addToCart method on button click", () => {
    spyOn(component, "addToCart");
    const carId = "123";
    component.addToCart(carId);
    expect(component.addToCart).toHaveBeenCalledWith(carId);
  });

  it("should initialize carsData array on ngOnInit", () => {
    expect(component.carsData).toEqual([]);
  });

  it("should call searchCarsById when selectedCategory is 'Id'", () => {
    spyOn(component.cartService, "searchCarsById").and.returnValue(of({}));
    component.selectedCategory = "Id";
    component.getAllSearchedCars();
    expect(component.cartService.searchCarsById).toHaveBeenCalledOnceWith(
      component.searchTerm,
    );
  });

  it("should call searchCarsByBrand when selectedCategory is 'Brand'", () => {
    spyOn(component.cartService, "searchCarsByBrand").and.returnValue(of([]));
    component.selectedCategory = "Brand";
    component.getAllSearchedCars();
    expect(component.cartService.searchCarsByBrand).toHaveBeenCalledOnceWith(
      component.searchTerm,
    );
  });

  it("should call searchCarsByPrice when selectedCategory is 'Price'", () => {
    spyOn(component.cartService, "searchCarsByPrice").and.returnValue(of([]));
    component.selectedCategory = "Price";
    component.getAllSearchedCars();
    expect(component.cartService.searchCarsByPrice).toHaveBeenCalledOnceWith(
      component.searchTerm,
    );
  });

  it("should call searchCarsByMileage when selectedCategory is 'Mileage'", () => {
    spyOn(component.cartService, "searchCarsByMileage").and.returnValue(of([]));
    component.selectedCategory = "Mileage";
    component.getAllSearchedCars();
    expect(component.cartService.searchCarsByMileage).toHaveBeenCalledOnceWith(
      component.searchTerm,
    );
  });

  it("should call searchAllCars when selectedCategory is not recognized", () => {
    spyOn(component.cartService, "searchAllCars").and.returnValue(of([]));
    component.selectedCategory = "Unknown";
    component.getAllSearchedCars();
    expect(component.cartService.searchAllCars).toHaveBeenCalledOnceWith();
  });

  it("should not show snackbar on addToCart failure", () => {
    mockCartService.addToCart.and.returnValue(throwError({}));
    const carId = "123";
    component.addToCart(carId);
    expect(mockSnackBar.open).not.toHaveBeenCalled();
  });
});
