import { Component } from "@angular/core";
import { CartService } from "../../../shared/services/cart.service";
import { Router } from "@angular/router";

/**
 * Header for the dashboard.
 */
@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.scss"],
})
export class HeaderComponent {
  /**
   * Creates an instance of HeaderComponent.
   * @param {CartService} cartService - The cart service for managing the shopping cart.
   * @param {Router} router - The Angular router for navigation.
   */
  constructor(
      public cartService: CartService,
      public router: Router,
  ) {}
  /** The title to be displayed in the header. */
  title: string = "Java UI Demo";
  /** The number of items in the shopping cart. */
  cartItemCount: number = 0;
  // Flag to control the visibility of the cart UI
  isCartUIVisible: boolean = false;

  // Angular lifecycle hook - called after construction
  ngOnInit() {
    // Check if the current route includes "cart" to determine cart UI visibility
    if (this.router.url.includes("cart")) this.isCartUIVisible = true;
  }

  // Method to get cart items from the cart service
  getCartItems() {
    this.cartService.getCartItems();
  }
}
