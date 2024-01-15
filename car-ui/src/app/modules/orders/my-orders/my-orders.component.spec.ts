import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MyOrdersComponent } from "./my-orders.component";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { MaterialModule } from "../../../shared/module/material.module";
import { RouterTestingModule } from "@angular/router/testing";
import { HttpClientModule } from "@angular/common/http";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { DatePipe } from "@angular/common";
import { CommonService } from "../../../shared/services/common.service";
import { CartService } from "../../../shared/services/cart.service";

describe("MyOrdersComponent", () => {
  let component: MyOrdersComponent;
  let fixture: ComponentFixture<MyOrdersComponent>;
  let commonService: CommonService;
  let cartService: CartService;
  let datePipe: DatePipe;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyOrdersComponent],
      imports: [
        HttpClientTestingModule,
        MaterialModule,
        RouterTestingModule,
        HttpClientModule,
      ],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
        DatePipe, // Add DatePipe to the providers array
        CommonService,
        CartService,
      ],
    });
    fixture = TestBed.createComponent(MyOrdersComponent);
    component = fixture.componentInstance;
    commonService = TestBed.inject(CommonService);
    cartService = TestBed.inject(CartService);
    datePipe = TestBed.inject(DatePipe);

    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should initialize component properties on ngOnInit", () => {
    spyOn(component, "getAllOrders");
    component.ngOnInit();
    expect(component.isCarsDataVisible).toEqual(true);
    expect(component.getAllOrders).toHaveBeenCalled();
  });

  it("should set headers for cars data on setHeadersForCarsData", () => {
    const event = {}; // Provide appropriate test data
    spyOn(component.commonService, "setTableHeaders").and.returnValue([]);
    component.setHeadersForCarsData(event);
    expect(component.updatedOrderHeaders).toEqual([]);
    expect(component.ordersColumnDef).toEqual([]);
  });

  it("should format timestamp correctly on formatTimestamp", () => {
    const timestamp = new Date();
    const formattedTimestamp = component.formatTimestamp(timestamp);

    expect(formattedTimestamp).toBeDefined();
  });

  it("should handle null timestamp in formatTimestamp", () => {
    const formattedTimestamp = component.formatTimestamp(null);
    expect(formattedTimestamp).toEqual("");
  });

  it("should handle undefined timestamp in formatTimestamp", () => {
    const formattedTimestamp = component.formatTimestamp(undefined);
    expect(formattedTimestamp).toEqual("");
  });

  it("should handle undefined order data in formatTimestamp", () => {
    const formattedTimestamp = component.formatTimestamp(undefined);
    expect(formattedTimestamp).toEqual("");
  });

  it("should handle a valid timestamp in formatTimestamp", () => {
    const timestamp = new Date();
    const formattedTimestamp = component.formatTimestamp(timestamp);

    expect(formattedTimestamp).toBeDefined();
    // Add expectations for the formatted timestamp
  });
});
