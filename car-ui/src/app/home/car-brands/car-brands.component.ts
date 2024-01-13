import { Component, OnDestroy, OnInit } from "@angular/core";
import { CarsListService } from "../services/cars-list.service";
import { CarDetailsService } from "../../shared/services/car-details.service";
import { CarBrand } from "../../shared/module/cars-details.model";
import { ActivatedRoute, Router } from "@angular/router";

/**
 * Component to display car brands and handle brand selection.
 */
@Component({
  selector: "app-car-brands",
  templateUrl: "./car-brands.component.html",
  styleUrls: ["./car-brands.component.scss"],
})
export class CarBrandsComponent implements OnInit, OnDestroy {
  carBrands: CarBrand[] = []; // Array to store car brand data
  selectedCloud: string = ""; // Currently selected car brand
  // sseSubscription!: Subscription;
  /**
   * Flag to control the visibility of the brand loader (spinner).
   */
  brandLoader: boolean = false;
  isNewUI: boolean = true; // Flag to determine if the component is using a new UI

  /**
   * Constructor of the component.
   *
   * @param carsService - The CarsListService to interact with data related to car brands.
   * @param carsDataService - The service to fetch car brand data.
   * @param route - The activated route to access route parameters.
   * @param router - The Angular router service.
   */
  constructor(
    private carsService: CarsListService,
    public carsDataService: CarDetailsService,
    public route: ActivatedRoute,
    public router: Router,
  ) {}

  /**
   * Lifecycle hook. Called when the component is initialized.
   */
  ngOnInit(): void {
    // Check if the component is being used in the "dashboard" route and update isNewUI accordingly
    if (this.router.url.includes("dashboard")) {
      this.isNewUI = false;
    }
    // Get the selected cloud from the route parameters and fetch car brands
    this.selectedCloud = this.route.snapshot.url[0].path;
    this.getCarBrands(this.selectedCloud);
  }

  /**
   * Sends the selected car brand name to the CarsListComponent.
   * @param name - The name of the selected car brand.
   */
  onBrandClick(name: string) {
    this.carsService.setBrandsName(name);
  }

  /**
   * Fetches car brands based on the selected cloud.
   * @param selectedCloud - The selected cloud for which car brands need to be fetched.
   */
  getCarBrands(selectedCloud: string): void {
    this.carsDataService.getCarBrands(selectedCloud).subscribe((brands) => {
      this.carBrands = brands;
      this.brandLoader = true;
    });
  }

  // getBrandsSSE(): void {
  //   this.sseSubscription = this.carsService.subscribeToCarData().subscribe(
  //     (carDetails: CarBrand) => {
  //       // Handle SSE data here
  //       // console.log("Received SSE data:", carDetails);
  //       this.carBrands.push(carDetails); // Add to your list of car details
  //       this.brandLoader = true;
  //       console.log("this.carBrands ", JSON.stringify(this.carBrands));
  //     },
  //     (error) => {
  //       // Handle SSE error
  //       console.error("Error occurred:", error);
  //     },
  //   );
  // }

  /**
   * Lifecycle hook. Called when the component is destroyed.
   * Unsubscribe from any subscriptions here (currently commented out).
   */
  ngOnDestroy() {}
}
