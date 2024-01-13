import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
/**
 * Service responsible for handling data related to the cars list.
 */
@Injectable({
  providedIn: "root",
})
export class CarsListService {
  // BehaviorSubject to hold the brands' names
  private brandsName = new BehaviorSubject<string>("");

  /**
   * Creates an instance of CarsListService.
   */
  constructor() {}

  /**
   * Sets the brands' names.
   * @param {string} brandsName - The brands' names to set.
   */
  setBrandsName(brandsName: string): void {
    this.brandsName.next(brandsName);
  }
}
