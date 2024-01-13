import { Component, Inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { CarsDataComponent } from "../../../modules/cloud-options/cars-data/cars-data.component";
@Component({
  selector: "app-confirmation-dialog",
  templateUrl: "./confirmation-dialog.component.html",
  styleUrls: ["./confirmation-dialog.component.scss"],
})
export class ConfirmationDialogComponent {
  // Injecting MAT_DIALOG_DATA to access data passed to the dialog
  constructor(@Inject(MAT_DIALOG_DATA) public data: CarsDataComponent) {}
}
