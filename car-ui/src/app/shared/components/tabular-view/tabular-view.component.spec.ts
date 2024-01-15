import { ComponentFixture, TestBed } from "@angular/core/testing";
import { TabularViewComponent } from "./tabular-view.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";

describe("TabularViewComponent", () => {
  let component: TabularViewComponent;
  let fixture: ComponentFixture<TabularViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TabularViewComponent],
      schemas: [NO_ERRORS_SCHEMA],
    });
    fixture = TestBed.createComponent(TabularViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should have default column definition", () => {
    expect(component.defaultColDef).toBeTruthy();
  });

  it("should have auto group column definition", () => {
    expect(component.autoGroupColumnDef).toBeTruthy();
  });

  it("should have overlay loading template", () => {
    expect(component.overlayLoadingTemplate).toBeTruthy();
  });

  it("should have overlay no rows template", () => {
    expect(component.overlayNoRowsTemplate).toBeTruthy();
  });

  it("should have pagination property", () => {
    expect(component.pagination).toBeDefined();
  });

  it("should have ngOnInit lifecycle hook", () => {
    expect(component.ngOnInit).toBeDefined();
  });
});
