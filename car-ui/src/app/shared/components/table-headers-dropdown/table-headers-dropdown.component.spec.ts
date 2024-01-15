// table-headers-dropdown.component.spec.ts

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MaterialModule } from "../../module/material.module";
import { NoopAnimationsModule } from "@angular/platform-browser/animations";
import { TableHeadersDropdownComponent } from "./table-headers-dropdown.component";

describe("TableHeadersDropdownComponent", () => {
  let component: TableHeadersDropdownComponent;
  let fixture: ComponentFixture<TableHeadersDropdownComponent>;

  const tableHeaders = [
    {
      field: "day-of-week",
      colId: "day",
      headerName: "Day of Week",
      filter: "agTextColumnFilter",
      suppressMenu: true,
      unSortIcon: true,
      hide: false,
    },
    {
      field: "shrink-event-count",
      colId: "totalShrinkEvents",
      headerName: "Total Shrink Items",
      filter: "agTextColumnFilter",
      suppressMenu: true,
      unSortIcon: true,
      hide: false,
    },
    {
      field: "bulk-event-count",
      colId: "bulkEventCount",
      headerName: "Bulk Events",
      filter: "agTextColumnFilter",
      suppressMenu: true,
      unSortIcon: true,
      hide: false,
    },
    {
      field: "sweetheart-count",
      colId: "sweetheartCount",
      headerName: "Sweetheart",
      filter: "agTextColumnFilter",
      suppressMenu: true,
      unSortIcon: true,
      hide: false,
    },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [MaterialModule, NoopAnimationsModule],
      declarations: [TableHeadersDropdownComponent],
    });
    fixture = TestBed.createComponent(TableHeadersDropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should set the fields of table headers", () => {
    component.tableHeaders = tableHeaders;
    const result = [
      "day",
      "totalShrinkEvents",
      "bulkEventCount",
      "sweetheartCount",
    ];
    component.ngOnChanges();
    expect(component.newSelectedTableHeaders).toEqual(result);
  });

  it("should emit the selected headers field", () => {
    component.newSelectedTableHeaders = [
      "day",
      "bulkEventCount",
      "sweetheartCount",
    ];
    spyOn(component.updatedHeaders, "emit");
    const event = {
      value: ["day", "bulkEventCount", "sweetheartCount"],
    };
    component.onColumnSelectionChange(event);
    expect(component.updatedHeaders.emit).toHaveBeenCalledWith(
      component.newSelectedTableHeaders,
    );
  });

  it("should toggle the settings dropdown", () => {
    component.showSettingsDropdown = false;
    component.toggleSettingsDropdown();
    expect(component.showSettingsDropdown).toBe(true);

    component.toggleSettingsDropdown();
    expect(component.showSettingsDropdown).toBe(false);
  });

  it("should toggle column selection", () => {
    component.newSelectedTableHeaders = ["day", "bulkEventCount"];
    const columnId = "sweetheartCount";

    component.toggleColumnSelection(columnId);
    expect(component.newSelectedTableHeaders).toEqual([
      "day",
      "bulkEventCount",
      "sweetheartCount",
    ]);

    component.toggleColumnSelection(columnId);
    expect(component.newSelectedTableHeaders).toEqual([
      "day",
      "bulkEventCount",
    ]);
  });

  it("should handle ngOnChanges when tableHeaders is not provided", () => {
    component.ngOnChanges();
    expect(component.newSelectedTableHeaders).toEqual([]);
  });

  it("should handle ngOnChanges when tableHeaders contains undefined colId", () => {
    const invalidTableHeaders = [
      {
        field: "invalid-field",
        colId: undefined,
        headerName: "Invalid Header",
      },
      // Add other valid headers if needed
    ];
    component.tableHeaders = invalidTableHeaders;
    component.ngOnChanges();
    expect(component.newSelectedTableHeaders).toEqual([]);
  });

  it("should handle toggleColumnSelection with a valid columnId already in the list", () => {
    const initialHeaders = ["day", "bulkEventCount"];
    component.newSelectedTableHeaders = initialHeaders;
    const existingColumnId = "bulkEventCount";
    component.toggleColumnSelection(existingColumnId);
    expect(component.newSelectedTableHeaders).toEqual(["day"]);
  });
});
