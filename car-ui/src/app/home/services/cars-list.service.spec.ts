import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import { CarsListService } from "./cars-list.service";

describe("CarsListService", () => {
  let service: CarsListService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CarsListService],
    });

    service = TestBed.inject(CarsListService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });

  // Test case to check if setBrandsName method sets the brands' names correctly
  it("should set the brands' names", () => {
    const testBrandsName = "Test Brands";
    // Calling the setBrandsName method
    service.setBrandsName(testBrandsName);
    // Expecting the brandsName BehaviorSubject to have the correct value
    service['brandsName'].subscribe((brandsName) => {
      expect(brandsName).toBe(testBrandsName);
    });
  });

  // Test case to check if setBrandsName method updates the brands' names correctly
  it("should update the brands' names", () => {
    const initialBrandsName = "Initial Brands";
    const updatedBrandsName = "Updated Brands";

    // Setting initial brands' names
    service.setBrandsName(initialBrandsName);

    // Calling the setBrandsName method to update brands' names
    service.setBrandsName(updatedBrandsName);

    // Expecting the brandsName BehaviorSubject to have the updated value
    service['brandsName'].subscribe((brandsName) => {
      expect(brandsName).toBe(updatedBrandsName);
    });
  });
});
