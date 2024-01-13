import { Component } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-page-not-found",
  templateUrl: "./page-not-found.component.html",
  styleUrls: ["./page-not-found.component.scss"],
})
export class PageNotFoundComponent {
  // Injecting Router service in the constructor
  constructor(private router: Router) {}

  // Method to navigate to the dashboard route
  goToDashboard() {
    this.router.navigate(["/dashboard"]);
  }
}
