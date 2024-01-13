import {
  ComponentFixture,
  fakeAsync,
  TestBed,
  tick,
} from "@angular/core/testing";

import { CartItemsComponent } from "./cart-items.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { RouterTestingModule } from "@angular/router/testing";
import { MaterialModule } from "../../../shared/module/material.module";
import { MatSnackBar } from "@angular/material/snack-bar";
import { of, throwError } from "rxjs";
describe("CartItemsComponent", () => {
  let component: CartItemsComponent;
  let fixture: ComponentFixture<CartItemsComponent>;
  let mockSnackBar: jasmine.SpyObj<MatSnackBar>;

  beforeEach(() => {
    mockSnackBar = jasmine.createSpyObj(["open"]);
    TestBed.configureTestingModule({
      declarations: [CartItemsComponent],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [HttpClientModule, RouterTestingModule, MaterialModule],
      providers: [{ provide: MatSnackBar, useValue: mockSnackBar }],
    });
    fixture = TestBed.createComponent(CartItemsComponent);
    component = fixture.componentInstance;
    component.cartItem = {
      productId: "1",
      brand: "Brand",
      model: "Model",
      color: "Red",
      tax: "0.1",
      basePrice: "100",
      quantity: "2",
    };
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
