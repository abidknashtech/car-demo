import { ComponentFixture, TestBed } from "@angular/core/testing";

import { PageNotFoundComponent } from "./page-not-found.component";

describe("PageNotFoundComponent", () => {
  let component: PageNotFoundComponent;
  let fixture: ComponentFixture<PageNotFoundComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageNotFoundComponent],
    });
    fixture = TestBed.createComponent(PageNotFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  // Test case to check if the goToDashboard method navigates to the correct route
  it("should navigate to /dashboard when goToDashboard is called", () => {
    // Spy on the router navigate method
    const navigateSpy = spyOn(component['router'], 'navigate');
    // Calling the goToDashboard method
    component.goToDashboard();
    // Expecting the navigate method to have been called with the correct route
    expect(navigateSpy).toHaveBeenCalledWith(['/dashboard']);
  });

  // Test case to check if the template contains the correct elements
  it("should contain a 404 message and a 'GO TO DASHBOARD' button", () => {
    // Accessing the compiled HTML from the fixture
    const compiled = fixture.nativeElement;
    // Expecting the HTML to contain a paragraph with the class 'wrong' and text '404'
    expect(compiled.querySelector('.wrong').textContent).toContain('404');
    // Expecting the HTML to contain a button with the text 'GO TO DASHBOARD'
    expect(compiled.querySelector('.go-to-dashboard').textContent).toContain('GO TO DASHBOARD');
  });
});
