import { Component, EventEmitter, Input, Output } from "@angular/core";
import { CartItem } from "../../../shared/module/cart-item.model";
import { CartService } from "../../../shared/services/cart.service";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "app-cart-items",
  templateUrl: "./cart-items.component.html",
  styleUrls: ["./cart-items.component.scss"],
})
export class CartItemsComponent {
  // Input property to receive cart item from parent component
  @Input() cartItem!: CartItem;
  // Output event emitter to notify parent component about placed orders
  @Output() OrderPlaced = new EventEmitter<CartItem>();

  // Constructor to inject necessary services
  constructor(
      public cartService: CartService,
      public snackBar: MatSnackBar,
  ) {}

  // Method to place an order
  placeOrder(productId: string, quantity: number) {
    this.cartService
      .makeOrder({
        productId: productId,
        quantity: quantity,
        userId: "1652",
      })
      .subscribe({
        next: (orderSummary) => {
          // Show success message using MatSnackBar
          this.snackBar.open("Order placed successfully", "Close", {
            duration: 2000,
          });
          // Emit the OrderPlaced event to notify the parent component
          this.OrderPlaced.emit(this.cartItem);
          // Remove the item from the database
          this.removeFromDatabase(productId, quantity, "1652");
          console.log("Order placed successfully:", orderSummary);
        },
        error: (error) => {
          // Show error message using MatSnackBar
          this.snackBar.open("Error placing order:", "Close", {
            duration: 2000,
          });
          console.error("Error placing order:", error);
        },
      });
  }

  // Method to remove an item from the database
  removeFromDatabase(productId: string, quantity: number, userId: string) {
    this.cartService.removeFromCart(productId, quantity, userId).subscribe({
      next: (response) => {
        // Decrement the cart item count in the cart service
        this.cartService.decrementCartItemCount(quantity);
        console.log("Item removed from database:", response);
      },
      error: (error) => {
        console.error("Error removing item from database:", error);
      },
    });
  }

  // Method to increase the quantity of a cart item
  increaseQuantity(cartItem: CartItem) {
    this.cartService.addToCart(cartItem.productId).subscribe(
      (response) => {
        // Increment the quantity of the cart item
        cartItem.quantity += 1;
        // Increment the cart item count in the cart service
        this.cartService.incrementCartItemCount();
        // Show success message using MatSnackBar
        this.snackBar.open("Quantity updated successfully", "Close", {
          duration: 2000,
        });
      },
      (error) => {
        console.error("Error updating quantity:", error);
      },
    );
  }

  // Method to decrease the quantity of a cart item
  decreaseQuantity(cartItem: CartItem) {
    this.cartService.removeFromCart(cartItem.productId, 1, "1652").subscribe(
      (response) => {
        if (Number(cartItem.quantity) == 1) {
          // If the quantity is 1, emit the OrderPlaced event and remove from the database
          this.OrderPlaced.emit(this.cartItem);
          this.removeFromDatabase(cartItem.productId, 1, "1652");
        } else {
          // Decrement the quantity of the cart item
          (cartItem as any).quantity -= 1;
          // Decrement the cart item count in the cart service
          this.cartService.decrementCartItemCount(1);
        }
        // Show success message using MatSnackBar
        this.snackBar.open("Item removed from cart", "Close", {
          duration: 2000,
        });
      },
      (error) => {
        console.error("Error removing item from cart:", error);
      },
    );
  }

  // Protected constant to store the Number class reference
  protected readonly Number = Number;
}
