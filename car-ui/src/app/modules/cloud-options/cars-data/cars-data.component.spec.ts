import {
  ComponentFixture,
  fakeAsync,
  TestBed,
  tick,
} from "@angular/core/testing";

import { CarsDataComponent } from "./cars-data.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { RouterTestingModule } from "@angular/router/testing";
import { MaterialModule } from "../../../shared/module/material.module";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatDialog } from "@angular/material/dialog";
import { of } from "rxjs";
// Import the component and services to be tested
import { CarDetailsService } from "../../../shared/services/car-details.service";
import { CommonService } from "../../../shared/services/common.service";

describe("CarsDataComponent", () => {
  let component: CarsDataComponent;
  let fixture: ComponentFixture<CarsDataComponent>;
  let carDetailsService: CarDetailsService;
  let commonService: CommonService;
  let matDialog: MatDialog;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarsDataComponent],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [
        HttpClientModule,
        RouterTestingModule,
        MaterialModule,
        BrowserAnimationsModule,
      ],
      providers: [
        CarDetailsService,
        CommonService,
        { provide: MatDialog, useValue: { open: () => {} } },
      ],
    });
    fixture = TestBed.createComponent(CarsDataComponent);
    component = fixture.componentInstance;
    carDetailsService = TestBed.inject(CarDetailsService);
    commonService = TestBed.inject(CommonService);
    matDialog = TestBed.inject(MatDialog);
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should handle error when loading car brands on ngOnInit", fakeAsync(() => {
    spyOn(carDetailsService, "getCarBrands").and.returnValue(of([]));

    component.ngOnInit();
    tick();

    expect(component.isCarsDataVisible).toBeFalse();
  }));

  it("should handle error when updating car models on car brand selection change", fakeAsync(() => {
    spyOn(carDetailsService, "getCarModels").and.returnValue(of([]));

    component.onCarBrandSelectionChange({ value: "Toyota" });
    tick();

    expect(component.carData).toEqual([]);
  }));

  it("should set default headers on setHeadersForCarsData", () => {
    const event = { target: { value: "default" } };
    spyOn(commonService, "setTableHeaders").and.returnValue([]);

    component.setHeadersForCarsData(event);

    expect(commonService.setTableHeaders).toHaveBeenCalledWith(
      event,
      component.allTableHeaders,
    );
    expect(component.carColumnDef).toEqual([]);
  });

  it("should handle error on setHeadersForCarsData", () => {
    const event = { target: { value: "default" } };
    spyOn(commonService, "setTableHeaders").and.throwError("An error occurred");

    expect(() => component.setHeadersForCarsData(event)).toThrowError(
      "An error occurred",
    );
    expect(component.carColumnDef).toEqual([]);
  });

  it("should handle error on car brand selection change", () => {
    spyOn(component, "getCarModels").and.throwError("An error occurred");

    expect(() =>
      component.onCarBrandSelectionChange({ value: "Toyota" }),
    ).toThrowError("An error occurred");
    expect(component.carData).toEqual([]);
  });

  // When the URL includes "gcp", the selected cloud is set to "gcp".
  it('should set selected cloud to "gcp" when URL includes "gcp"', () => {
    spyOnProperty(component.router, 'url', 'get').and.returnValue("/dashboard/gcp");
    component.ngOnInit();
    expect(component.selectedCloud).toEqual("gcp");
  });

  // Method successfully retrieves car brands based on selected cloud
  it('should retrieve car brands when no car brands are retrieved', () => {
    const selectedCloud = "aws";

    spyOn(carDetailsService, 'getCarBrands').and.returnValue(of([]));
    component.getCarBrands(selectedCloud);
    expect(component.carBrands.length).toBe(0);
    expect(component.isCarsDataVisible).toBe(false);
  });

  it('should retrieve car brands when no car brands are retrieved', () => {
    const selectedCloud = "aws";

    spyOn(carDetailsService, 'getCarBrands').and.returnValue(of([{brand: "Audi"}]));
    component.getCarBrands(selectedCloud);
    expect(component.carBrands.length).toBeGreaterThan(0);
    expect(component.isCarsDataVisible).toBe(true);
  });
});
