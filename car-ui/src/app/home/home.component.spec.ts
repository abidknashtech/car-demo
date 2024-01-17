import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MatToolbarModule } from "@angular/material/toolbar"; // Import the MatToolbarModule

import { HomeComponent } from "./home.component";
import { CarsListComponent } from "./cars-list/cars-list.component";
import { HttpClientModule } from "@angular/common/http";
import { RouterTestingModule } from "@angular/router/testing";
import { NO_ERRORS_SCHEMA } from "@angular/core";

describe("HomeComponent", () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeComponent, CarsListComponent],
      imports: [RouterTestingModule, HttpClientModule, MatToolbarModule],
      schemas: [NO_ERRORS_SCHEMA],
    });
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  // When the router URL does not include "dashboard", the isHeaderVisible property should be true by default.
  it('should set isHeaderVisible to true when router URL does not include "dashboard"', () => {
    spyOnProperty(component.router, 'url', 'get').and.returnValue("/home");
    component.ngOnInit();
    expect(component.isHeaderVisible).toBe(true);
  });

  // When the router URL includes "dashboard", the isHeaderVisible property should be set to false.
  it('should set isHeaderVisible to false when router URL includes "dashboard"', () => {
    spyOnProperty(component.router, 'url', 'get').and.returnValue("/dashboard");
    component.ngOnInit();
    expect(component.isHeaderVisible).toBe(false);
  });
});
