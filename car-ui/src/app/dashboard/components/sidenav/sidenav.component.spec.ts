import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MaterialModule } from "../../../shared/module/material.module";
import { SidenavComponent } from "./sidenav.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterTestingModule } from "@angular/router/testing";

describe("SidenavComponent", () => {
  let component: SidenavComponent;
  let fixture: ComponentFixture<SidenavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SidenavComponent],
      imports: [MaterialModule, BrowserAnimationsModule, RouterTestingModule],
    });
    fixture = TestBed.createComponent(SidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it('should toggle menu state and update arrow icon', () => {
    // Initial state
    expect(component.isMenuOpen).toBe(true);
    expect(component.arrowIcon).toBe('keyboard_double_arrow_left');
    expect(component.contentMargin).toBe(240);

    // Trigger toggle
    component.onToolbarMenuToggle();

    // After toggle
    expect(component.isMenuOpen).toBe(false);
    expect(component.arrowIcon).toBe('keyboard_double_arrow_right');
    expect(component.contentMargin).toBe(70);

    // Trigger toggle again
    component.onToolbarMenuToggle();

    // Back to initial state
    expect(component.isMenuOpen).toBe(true);
    expect(component.arrowIcon).toBe('keyboard_double_arrow_left');
    expect(component.contentMargin).toBe(240);
  });
});
