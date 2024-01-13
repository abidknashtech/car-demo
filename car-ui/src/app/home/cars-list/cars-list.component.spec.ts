// Import necessary testing modules and classes
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute, Router } from "@angular/router";
import { of } from "rxjs";

// Import the component to be tested
import { CarsListComponent } from "./cars-list.component";

// Import any additional services or modules used in the component
import { CarDetailsService } from "../../shared/services/car-details.service";
import { CarDetails } from "../../shared/module/cars-details.model";

describe("CarsListComponent", () => {
  let component: CarsListComponent;
  let fixture: ComponentFixture<CarsListComponent>;
  let carDetailsService: jasmine.SpyObj<CarDetailsService>;
  let route: ActivatedRoute;
  let router: Router;

  // Set up a spy for CarDetailsService and configure TestBed
  beforeEach(() => {
    const carDetailsServiceSpy = jasmine.createSpyObj("CarDetailsService", [
      "getCarModels",
    ]);
    // Create a mock Router with a dummy url property
    const mockRouter = {
      url: "/dashboard", // Set a dummy value for testing
    };
    TestBed.configureTestingModule({
      declarations: [CarsListComponent],
      providers: [
        { provide: CarDetailsService, useValue: carDetailsServiceSpy },
        {
          provide: ActivatedRoute,
          useValue: { snapshot: { url: [{ path: "samplePath" }] } },
        },
        { provide: Router, useValue: mockRouter },
      ],
    });

    fixture = TestBed.createComponent(CarsListComponent);
    component = fixture.componentInstance;
    carDetailsService = TestBed.inject(
      CarDetailsService,
    ) as jasmine.SpyObj<CarDetailsService>;
    route = TestBed.inject(ActivatedRoute);
    router = TestBed.inject(Router);
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should initialize with default values", () => {
    expect(component.brandName).toEqual("");
    expect(component.isNewUI).toEqual(true);
    expect(component.showcarList).toEqual(false);
    // Add more expectations for other default values
  });

  it("should fetch car models on ngOnInit", () => {
    const carDetails: CarDetails[] = [];
    carDetailsService.getCarModels.and.returnValue(of(carDetails));

    component.ngOnInit();

    expect(component.isNewUI).toEqual(false);
    expect(component.selectedCloud).toEqual("samplePath");
    expect(component.selectedCarBrand).toEqual("");
    expect(carDetailsService.getCarModels).toHaveBeenCalledWith(
      "samplePath",
      "",
    );
    expect(component.carModelDetails).toEqual(carDetails);
    expect(component.showcarList).toEqual(true);
  });
});
