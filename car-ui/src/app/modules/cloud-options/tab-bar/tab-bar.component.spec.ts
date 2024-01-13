import { ComponentFixture, TestBed } from "@angular/core/testing";
import { TabBarComponent } from "./tab-bar.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { RouterTestingModule } from "@angular/router/testing";
import { MaterialModule } from "../../../shared/module/material.module";

describe("TabBarComponent", () => {
  let component: TabBarComponent;
  let fixture: ComponentFixture<TabBarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TabBarComponent],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [HttpClientModule, RouterTestingModule, MaterialModule],
    });
    fixture = TestBed.createComponent(TabBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should have a defined matchOptions", () => {
    expect(component.matchOptions).toBeDefined();
  });

  it("should have a defined shrinkTabs array", () => {
    expect(component.shrinkTabs).toBeDefined();
    expect(component.shrinkTabs.length).toBeGreaterThan(0);
  });

  it("should render tab links in the template", () => {
    const compiled = fixture.nativeElement;
    const tabLinks = compiled.querySelectorAll("a[mat-tab-link]");
    expect(tabLinks.length).toBe(component.shrinkTabs.length);
  });
});
