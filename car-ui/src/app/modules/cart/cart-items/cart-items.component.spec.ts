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
import { CartService } from "../../../shared/services/cart.service";
import { of, throwError } from "rxjs";
describe("CartItemsComponent", () => {
  let component: CartItemsComponent;
  let fixture: ComponentFixture<CartItemsComponent>;
  let mockSnackBar: jasmine.SpyObj<MatSnackBar>;
  let mockCartService: jasmine.SpyObj<CartService>;

  beforeEach(() => {
    mockSnackBar = jasmine.createSpyObj(["open"]);
    mockCartService = jasmine.createSpyObj("CartService", [
      "makeOrder",
      "removeFromCart",
      "addToCart",
    ]);

    TestBed.configureTestingModule({
      declarations: [CartItemsComponent],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [HttpClientModule, RouterTestingModule, MaterialModule],
      providers: [
        { provide: MatSnackBar, useValue: mockSnackBar },
        { provide: CartService, useValue: mockCartService },
      ],
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

  it("should handle error when placeOrder encounters an error", () => {
    const errorMessage = "Error placing order";
    mockCartService.makeOrder.and.returnValue(throwError(errorMessage));

    spyOn(console, "error");

    component.placeOrder("1", 2);

    expect(console.error).toHaveBeenCalledWith("Error placing order:", errorMessage);
  });
  it("should handle error when increaseQuantity encounters an error", () => {
    const errorMessage = "Error updating quantity";
    mockCartService.addToCart.and.returnValue(throwError(errorMessage));

    spyOn(console, "error");

    component.increaseQuantity(component.cartItem);

    expect(console.error).toHaveBeenCalledWith("Error updating quantity:", errorMessage);
  });
  it("should emit OrderPlaced event and call removeFromDatabase when quantity is 1 in decreaseQuantity", () => {
    const response = {}; // mocked response
    mockCartService.removeFromCart.and.returnValue(of(response));

    spyOn(component.OrderPlaced, "emit");
    spyOn(component, "removeFromDatabase");

    component.cartItem.quantity = "1";
    component.decreaseQuantity(component.cartItem);

    expect(component.OrderPlaced.emit).toHaveBeenCalledWith(component.cartItem);
    expect(component.removeFromDatabase).toHaveBeenCalledWith(
        component.cartItem.productId,
        1,
        "1652"
    );
  });
});
