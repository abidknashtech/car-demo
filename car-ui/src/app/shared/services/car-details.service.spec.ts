import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import { CarDetailsService } from "./car-details.service";
import { CarBrand, CarDetails } from "../module/cars-details.model";

describe("CarDetailsService", () => {
  let service: CarDetailsService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(CarDetailsService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });

  it("should return car brands from GCP", () => {
    const mockCarBrands: CarBrand[] = [{ brand: "Toyota" }, { brand: "Honda" }];

    service.getCarBrands("gcp").subscribe((carBrands) => {
      expect(carBrands).toEqual(mockCarBrands);
    });

    const req = httpTestingController.expectOne(
      "http://localhost:8080/v1/data/brands",
    );
    expect(req.request.method).toEqual("GET");
    req.flush(mockCarBrands);
  });

  it("should return an empty array when there is an error fetching car brands", () => {
    service.getCarBrands("gcp").subscribe((carBrands) => {
      expect(carBrands).toEqual([]);
    });

    const req = httpTestingController.expectOne(
      "http://localhost:8080/v1/data/brands",
    );
    expect(req.request.method).toEqual("GET");
    req.error(new ErrorEvent("Network error"));
  });

  it("should return car models from Azure", () => {
    const mockCarModels: CarDetails[] = [
      {
        carId: 1,
        brand: "Brand",
        model: "Model",
        year: 2024,
        color: "Red",
        mileage: 545,
        price: "64654",
      },
      {
        carId: 2,
        brand: "Brand",
        model: "Model",
        year: 2024,
        color: "Red",
        mileage: 545,
        price: "64654",
      },
    ];

    service.getCarModels("azure", "Toyota").subscribe((carModels) => {
      expect(carModels).toEqual(mockCarModels);
    });

    const req = httpTestingController.expectOne(
      "http://localhost:8080/v1/data/cars/Toyota",
    );
    expect(req.request.method).toEqual("GET");
    req.flush(mockCarModels);
  });

  it("should add bulk data to GCP", () => {
    service.addBulkData("gcp").subscribe((response) => {
      expect(response).toBeTruthy();
    });

    const req = httpTestingController.expectOne(
      "http://localhost:8080/v1/data",
    );
    expect(req.request.method).toEqual("POST");
    req.flush({ success: true });
  });

  it("should handle error when adding bulk data fails", () => {
    service.addBulkData("gcp").subscribe((response) => {
      expect(response).toBeFalsy();
    });

    const req = httpTestingController.expectOne(
      "http://localhost:8080/v1/data",
    );
    expect(req.request.method).toEqual("POST");
    req.error(new ErrorEvent("Internal Server Error"));
  });
});
