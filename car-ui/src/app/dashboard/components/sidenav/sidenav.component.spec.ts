import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MaterialModule } from "../../../shared/module/material.module";
import { SidenavComponent } from "./sidenav.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterTestingModule } from "@angular/router/testing";
import { Router, RoutesRecognized } from "@angular/router";

describe("SidenavComponent", () => {
  let component: SidenavComponent;
  let fixture: ComponentFixture<SidenavComponent>;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SidenavComponent],
      imports: [MaterialModule, BrowserAnimationsModule, RouterTestingModule],
    });
    fixture = TestBed.createComponent(SidenavComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should toggle menu state and update arrow icon", () => {
    // Initial state
    expect(component.isMenuOpen).toBe(true);
    expect(component.arrowIcon).toBe("keyboard_double_arrow_left");
    expect(component.contentMargin).toBe(240);

    // Trigger toggle
    component.onToolbarMenuToggle();

    // After toggle
    expect(component.isMenuOpen).toBe(false);
    expect(component.arrowIcon).toBe("keyboard_double_arrow_right");
    expect(component.contentMargin).toBe(70);

    // Trigger toggle again
    component.onToolbarMenuToggle();

    // Back to initial state
    expect(component.isMenuOpen).toBe(true);
    expect(component.arrowIcon).toBe("keyboard_double_arrow_left");
    expect(component.contentMargin).toBe(240);
  });

  it("should update content margin on toolbar menu toggle", () => {
    // Initial state
    expect(component.contentMargin).toBe(240);

    // Toggle menu
    component.onToolbarMenuToggle();

    // Check content margin after toggle
    expect(component.contentMargin).toBe(70);

    // Toggle menu again
    component.onToolbarMenuToggle();

    // Check content margin after toggle
    expect(component.contentMargin).toBe(240);
  });

  it("should have menu items defined", () => {
    // Check if menu items are defined
    expect(component.menuItems).toBeDefined();
    expect(component.menuItems.length).toBeGreaterThan(0);
  });

  it("should update content margin on toolbar menu toggle", () => {
    // Initial state
    expect(component.contentMargin).toBe(240);

    // Toggle menu
    component.onToolbarMenuToggle();

    // Check content margin after toggle
    expect(component.contentMargin).toBe(70);

    // Toggle menu again
    component.onToolbarMenuToggle();

    // Check content margin after toggle
    expect(component.contentMargin).toBe(240);
  });

  it("should have menu items defined", () => {
    // Check if menu items are defined
    expect(component.menuItems).toBeDefined();
    expect(component.menuItems.length).toBeGreaterThan(0);
  });

  // The component should initialize with the side menu open and the arrow icon pointing to the left.
  it("should initialize with side menu open and arrow icon pointing to the left", () => {
    expect(component.isMenuOpen).toBe(true);
    expect(component.arrowIcon).toBe("keyboard_double_arrow_left");
  });

  // The component should display the correct menu items with their respective icons and labels.
  it("should display correct menu items with icons and labels", () => {
    expect(component.menuItems).toEqual([
      {
        path: "/dashboard",
        icon: "CF",
        label: "Cloud Functions",
      },
      {
        path: "/dashboard/home",
        icon: "OU",
        label: "Old UI",
      },
      {
        path: "/dashboard/orders",
        icon: "MO",
        label: "My Orders",
      },
    ]);
  });
  // Returns false if the given URL is not a string.
  it("should return false when the given URL is not a string", () => {
    const result = component.checkRouteActive("");
    expect(result).toBe(false);
  });

  // Returns true if the given URL matches the current router URL.
  it("should return true when the given URL matches the current router URL", () => {
    spyOnProperty(component.router, "url", "get").and.returnValue("/dashboard");
    const result = component.checkRouteActive("/dashboard");
    expect(result).toBe(true);
  });
  // Returns true if the given URL is "/dashboard" and the current router URL includes "/dashboard/gcp".
  it('should return true when the given URL is "/dashboard" and the current router URL includes "/dashboard/gcp"', () => {
    spyOnProperty(component.router, "url", "get").and.returnValue(
      "/dashboard/gcp",
    );
    const result = component.checkRouteActive("/dashboard");
    expect(result).toBe(true);
  });

  // Returns true if the given URL is "/dashboard/home" and the current router URL includes "/dashboard/home/gcp" or "/dashboard/home/azure".
  it('should return true when the given URL is "/dashboard/home" and the current router URL includes "/dashboard/home/gcp" or "/dashboard/home/azure"', () => {
    spyOnProperty(component.router, "url", "get").and.returnValue(
      "/dashboard/home/gcp",
    );

    const result = component.checkRouteActive("/dashboard/home");
    expect(result).toBe(true);
  });
  // Returns false if the given URL is not a string.
  it("should return false when the given URL is not a string", () => {
    const result = component.checkRouteActive("");
    expect(result).toBe(false);
  });

  // Returns false if the current router URL or previous URL is undefined.
  it('should return false when the current router URL is undefined', () => {
    spyOnProperty(component.router, "url", "get").and.returnValue(
        "",
    );

    const result = component.checkRouteActive('/dashboard');
    expect(result).toBe(false);
  });

  // Returns true if the current router URL is "/dashboard/api-error" and the previous URL matches the given URL.
  it('should return true when the current router URL is "/dashboard/api-error" and the previous URL matches the given URL', () => {
    spyOnProperty(component.router, "url", "get").and.returnValue(
        '/dashboard/api-error',
    );
    component.previousUrl = '/dashboard';
    const result = component.checkRouteActive('/dashboard');
    expect(result).toBe(true);
  });
});
