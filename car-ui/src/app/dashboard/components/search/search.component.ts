import { Component, ElementRef, ViewChild } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { CartService } from "../../../shared/services/cart.service";

// Component decorator to define the metadata for the component
@Component({
  selector: "app-search",
  templateUrl: "./search.component.html",
  styleUrls: ["./search.component.scss"],
})
// Class definition for the SearchComponent
export class SearchComponent {
  // ViewChild decorators to get references to HTML elements
  @ViewChild("dropdownButton") dropdownButton!: ElementRef;
  @ViewChild("dropdownContent") dropdownContent!: ElementRef;

  // Arrays to store available items and filtered items
  items: string[] = ["All", "Brand", "Price", "Mileage", "Id"];
  filteredItems: string[] = [];
  // String to store the search term and selected category
  searchTerm: string = "";
  selectedCategory: string = "All";

  // Constructor to inject services (Router, CartService, ActivatedRoute)
  constructor(
    public router: Router,
    private cartService: CartService,
    private route: ActivatedRoute,
  ) {}

  // Lifecycle hook to initialize the component
  ngOnInit() {
    // Filter items to exclude the selected category
    this.filteredItems = this.items.filter(
      (item) => item !== this.selectedCategory,
    );
  }

  // Method to filter items based on the search term
  filterItems() {
    console.log("Search for :  ", this.searchTerm);
  }

  // Method to handle menu item clicks
  menuItemClicked(item: string) {
    console.log("Clicked:", item);
    this.selectedCategory = item;
    this.filteredItems = this.items.filter(
      (item) => item !== this.selectedCategory,
    );
  }

  // Method to handle search button clicks and navigate to search result page
  searchButtonClicked() {
    this.router.navigate(["/search-result"], {
      queryParams: {
        ["category"]: this.selectedCategory,
        ["term"]: this.searchTerm,
      },
    });
  }
}
