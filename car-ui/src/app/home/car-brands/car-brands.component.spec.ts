import { ComponentFixture, TestBed } from "@angular/core/testing";
import { CarBrandsComponent } from "./car-brands.component";
import { CarsListService } from "../services/cars-list.service";
import { RouterTestingModule } from "@angular/router/testing";
import { HttpClientModule } from "@angular/common/http";
import { MaterialModule } from "../../shared/module/material.module";
import { ActivatedRoute, convertToParamMap } from "@angular/router";
import {of, throwError} from "rxjs";
describe("CarBrandsComponent", () => {
  let component: CarBrandsComponent;
  let fixture: ComponentFixture<CarBrandsComponent>;
  let mockCarsListService: jasmine.SpyObj<CarsListService>;

  beforeEach(() => {
    mockCarsListService = jasmine.createSpyObj("CarsListService", [
      "getBrandName",
      "setBrandsName",
    ]);

    TestBed.configureTestingModule({
      declarations: [CarBrandsComponent],
      providers: [
        { provide: CarsListService, useValue: mockCarsListService },
        {
          provide: ActivatedRoute,
          useValue: { snapshot: { url: [{ path: "mockPath" }] } },
        },
      ],
      imports: [RouterTestingModule, HttpClientModule, MaterialModule],
    });

    fixture = TestBed.createComponent(CarBrandsComponent);
    component = fixture.componentInstance;
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should call setBrandsName when a brand is clicked", () => {
    const brandName = "Brand3";

    component.onBrandClick(brandName);

    expect(mockCarsListService.setBrandsName).toHaveBeenCalledWith(brandName);
  });


  it("should fetch car brands on getCarBrands", () => {
    const selectedCloud = "cloud1";
    const mockCarBrands = [{ brand: "Brand1" }, { brand: "Brand2" }];

    spyOn(component.carsDataService, "getCarBrands").and.returnValue(of(mockCarBrands));

    component.getCarBrands(selectedCloud);

    expect(component.carBrands).toEqual(mockCarBrands);
    expect(component.brandLoader).toBeTruthy();
  });

  it("should handle new UI when 'dashboard' is not in the URL", () => {
    spyOn(component.router.url, "includes").and.returnValue(false);

    component.ngOnInit();

    expect(component.isNewUI).toBeTruthy();
  });
});
