import { Component } from '@angular/core';
import {ColDef, ColGroupDef} from "ag-grid-community";
import {CarBrand} from "../../../shared/module/cars-details.model";
import {Subscription} from "rxjs";
import {constants} from "../../../constants/constants";
import {FormControl} from "@angular/forms";
import {CommonService} from "../../../shared/services/common.service";
import {CarDetailsService} from "../../../shared/services/car-details.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {ActionColumnComponent} from "../../../shared/components/action-column/action-column.component";
import {
  ConfirmationDialogComponent
} from "../../../dashboard/components/confirmation-dialog/confirmation-dialog.component";


@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.scss']
})
export class MyOrdersComponent {
  carColumnDef: (ColDef | ColGroupDef)[] = [];
  allTableHeaders: any[] = [];
  updatedCarHeaders: any[] = [];
  isLoading = false;
  // carModelsData: CarsData[] = [];
  carBrands: CarBrand[] = [];
  carDataSubscription$!: Subscription;
  selectedCloud: string = constants.defaultCloud;
  carBrandControl = new FormControl();
  selectedCarBrand!: string;
  carData: any[] = [];
  isCarsDataVisible: boolean = true;

  constructor(
      public commonService: CommonService,
      private carsDataService: CarDetailsService,
      private router: Router,
      public dialog: MatDialog,
  ) {
  }

  ngOnInit(): void {
    if (this.router.url.includes("gcp")) {
      this.selectedCloud = "gcp";
    }
    this.getCarBrands(this.selectedCloud);
  }

  setHeadersForCarsData(event: any) {
    this.updatedCarHeaders = [];
    this.updatedCarHeaders = this.commonService.setTableHeaders(
        event,
        this.allTableHeaders,
    );
    this.carColumnDef = this.updatedCarHeaders;
  }

  getCarBrands(selectedCloud: string) {
    this.isLoading = true;
    this.carsDataService.getCarBrands(selectedCloud).subscribe((brands) => {
      this.carBrands = brands;
      if (this.carBrands.length === 0) this.isCarsDataVisible = false;
      else {
        const preSelectedBrand = this.carBrands[0].brand;
        this.carBrandControl.setValue(preSelectedBrand);
        this.getCarModels(selectedCloud, preSelectedBrand);
        this.isLoading = false;
        this.isCarsDataVisible = true;
      }
    });
  }

  getCarModels(selectedCloud: string, brand: string) {
    this.isLoading = true;
    this.carDataSubscription$ = this.carsDataService
        .getCarModels(selectedCloud, brand)
        .subscribe((res) => {
          if (res) {
            this.carColumnDef = [
              {
                field: "carId",
                headerName: "Car Id",
                colId: "carId",
                minWidth: 100,
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "brand",
                headerName: "Brand",
                colId: "brand",
                minWidth: 170,
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "model",
                headerName: "Model",
                colId: "model",
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "year",
                headerName: "Year",
                colId: "year",
                minWidth: 100,
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "color",
                headerName: "Color",
                colId: "color",
                minWidth: 150,
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "price",
                headerName: "Price",
                colId: "price",
                minWidth: 130,
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "mileage",
                headerName: "Mileage",
                colId: "mileage",
                filter: "agTextColumnFilter",
                suppressMenu: true,
                unSortIcon: true,
              },
              {
                field: "Action",
                headerName: "Action",
                colId: "Action",
                cellRenderer: ActionColumnComponent,
                floatingFilter: false,
              },
            ];
            this.carData = res;
            this.updatedCarHeaders = this.carColumnDef;
            this.allTableHeaders = this.carColumnDef;
          }
          this.isLoading = false;
        });
  }
  openDialog() {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        selectedCloud: this.selectedCloud,
      },
    });

    dialogRef.afterClosed().subscribe(() => {
      const url: string =
          this.selectedCloud === "gcp" ? "/dashboard/gcp" : "/dashboard";
      this.router
          .navigateByUrl("/home", {skipLocationChange: true})
          .then(() => {
            this.router.navigate([url]);
          });
    });
  }
}
