// action-column.component.spec.ts

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActionColumnComponent } from "./action-column.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { MaterialModule } from "../../module/material.module";
import { DatePipe } from "@angular/common";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { CookieModule, CookieService } from "ngx-cookie";
import { ICellRendererParams } from "ag-grid-community";
import { Observable, of } from "rxjs";

describe("ActionColumnComponent", () => {
  let component: ActionColumnComponent;
  let fixture: ComponentFixture<ActionColumnComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActionColumnComponent],
      imports: [
        HttpClientTestingModule,
        CookieModule.forRoot(),
        MaterialModule,
      ],
      providers: [CookieService, DatePipe],
      schemas: [NO_ERRORS_SCHEMA],
    });
    fixture = TestBed.createComponent(ActionColumnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should initialize bulkEventCellValue on agInit", () => {
    const paramsMock = { data: { carId: "TestCarId" } };
    component.agInit(<ICellRendererParams>paramsMock);
    expect(component.bulkEventCellValue).toEqual(paramsMock);
    expect(component.carId).toEqual("TestCarId");
  });

  it("should show snackbar with a custom message on showSnackBar", () => {
    spyOn(component.snackBar, "open");

    component.showSnackBar("Custom Message");

    expect(component.snackBar.open).toHaveBeenCalledWith(
      "Custom Message",
      "Close",
      { duration: 3000 },
    );
  });

  it("should initialize bulkEventCellValue on agInit", () => {
    const paramsMock = { data: { carId: "TestCarId" } };
    component.agInit(<ICellRendererParams>paramsMock);
    expect(component.bulkEventCellValue).toEqual(paramsMock);
    expect(component.carId).toEqual("TestCarId");
  });

  it("should log an error message if addToCart fails", () => {
    spyOn(component.cartService, "addToCart").and.returnValue(of(null));
    spyOn(console, "error");

    component.addToCart();

    expect(console.error).toHaveBeenCalledWith("Failed to add to cart:");
  });

  it("should call showSnackBar with a custom message", () => {
    spyOn(component.snackBar, "open");

    component.showSnackBar("Custom Message");

    expect(component.snackBar.open).toHaveBeenCalledWith(
      "Custom Message",
      "Close",
      { duration: 3000 },
    );
  });
  it("should handle refresh and return true", () => {
    const paramsMock = { data: { carId: "TestCarId" } };
    const result = component.refresh(<ICellRendererParams>paramsMock);
    expect(component.bulkEventCellValue).toEqual(paramsMock);
    expect(result).toBeTruthy();
  });

  it("should call refresh and return true", () => {
    const paramsMock = { data: { carId: "TestCarId" } };
    const result = component.refresh(<ICellRendererParams>paramsMock);
    expect(component.bulkEventCellValue).toEqual(paramsMock);
    expect(result).toBeTruthy();
  });

  it("should handle addToCart with a null response", () => {
    spyOn(component.cartService, "addToCart").and.returnValue(of(null));
    spyOn(component.cartService, "incrementCartItemCount");
    spyOn(component, "showSnackBar");
    spyOn(console, "error");

    component.addToCart();

    expect(component.cartService.addToCart).toHaveBeenCalledWith(
      component.carId,
    );
    expect(component.cartService.incrementCartItemCount).not.toHaveBeenCalled();
    expect(component.showSnackBar).not.toHaveBeenCalled();
    expect(console.error).toHaveBeenCalledWith("Failed to add to cart:");
  });
});
