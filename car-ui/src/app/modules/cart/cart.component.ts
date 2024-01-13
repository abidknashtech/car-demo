import { Component } from "@angular/core";
import { CartItem } from "../../shared/module/cart-item.model";
import { CartService } from "../../shared/services/cart.service";

// Decorator to define the component metadata
@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.scss"],
})
export class CartComponent {
  // Array to hold cart items
  cartItems: CartItem[] = [];

  // Constructor to inject the CartService
  constructor(private cartService: CartService) {}

  // Lifecycle hook - ngOnInit is called after the component is initialized
  ngOnInit() {
    this.getCartItem();
  }

  // Method to fetch cart items from the CartService
  getCartItem() {
    this.cartService.getCartItems().subscribe((cartData) => {
      this.cartItems = cartData;
    });
  }

  // Custom trackBy function to improve ngFor performance
  trackByFn(index: number, item: CartItem) {
    return item.model; // Assuming you have a unique identifier like 'id'
  }

  // Method to handle order placement and remove item from the cart
  orderPlaced(cartItem: CartItem) {
    this.cartItems = this.cartItems.filter((item) => cartItem !== item);
  }
}
