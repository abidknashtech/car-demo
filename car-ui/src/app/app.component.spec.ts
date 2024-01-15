import { ComponentFixture, TestBed , fakeAsync, tick } from "@angular/core/testing";
import { RouterTestingModule } from "@angular/router/testing";
import { AppComponent } from "./app.component";
import {
  Router,
  NavigationStart,
  NavigationEnd,
  NavigationCancel,
  NavigationError,
} from '@angular/router';
describe("AppComponent", () => {
  let fixture: ComponentFixture<AppComponent>;
  let app: AppComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],

      declarations: [AppComponent],
    });
    fixture = TestBed.createComponent(AppComponent);
    app = fixture.componentInstance;
  });

  it("should create the app", () => {
    expect(app).toBeTruthy();
  });

  it(`should have as title 'java-competency-demo-ui'`, () => {
    expect(app.title).toEqual("Java-compentency-ui");
  });

  it("should initialize loading as false", () => {
    expect(app.loading).toBeFalse();
  });
  it('should set loading to true on NavigationStart event', () => {
    const router = TestBed.inject(Router);
    const navigationStart = new NavigationStart(1, 'some-url');
    (router.events as any).next(navigationStart);

    expect(app.loading).toBeTrue();
  });

  it('should set loading to true on NavigationStart event', fakeAsync(() => {
    const router = TestBed.inject(Router);
    const navigationStart = new NavigationStart(1, 'some-url');
    (router.events as any).next(navigationStart);
    tick(); // Simulate async behavior
    expect(app.loading).toBeTrue();
  }));

  it('should set loading to false on NavigationEnd event', () => {
    const router = TestBed.inject(Router);
    const navigationEnd = new NavigationEnd(1, 'some-url', 'some-url');
    (router.events as any).next(navigationEnd);

    expect(app.loading).toBeFalse();
  });

  it('should set loading to false on NavigationCancel event', () => {
    const router = TestBed.inject(Router);
    const navigationCancel = new NavigationCancel(1, 'some-url', 'reason');
    (router.events as any).next(navigationCancel);

    expect(app.loading).toBeFalse();
  });

  it('should set loading to false on NavigationError event', () => {
    const router = TestBed.inject(Router);
    const navigationError = new NavigationError(1, 'some-url', 'error');
    (router.events as any).next(navigationError);

    expect(app.loading).toBeFalse();
  });

  it('should not change loading for unknown event type', () => {
    const router = TestBed.inject(Router);
    const unknownEvent = {} as any;
    (router.events as any).next(unknownEvent);

    expect(app.loading).toBeFalse();
  });

  it('should handle multiple NavigationStart events', () => {
    const router = TestBed.inject(Router);

    const navigationStart1 = new NavigationStart(1, 'some-url-1');
    (router.events as any).next(navigationStart1);
    expect(app.loading).toBeTrue();

    const navigationStart2 = new NavigationStart(2, 'some-url-2');
    (router.events as any).next(navigationStart2);
    expect(app.loading).toBeTrue();

    const navigationEnd = new NavigationEnd(3, 'some-url-2', 'some-url-2');
    (router.events as any).next(navigationEnd);
    expect(app.loading).toBeFalse();
  });

  it('should handle NavigationStart event after NavigationEnd', () => {
    const router = TestBed.inject(Router);

    const navigationEnd = new NavigationEnd(1, 'some-url', 'some-url');
    (router.events as any).next(navigationEnd);
    expect(app.loading).toBeFalse();

    const navigationStart = new NavigationStart(2, 'some-url');
    (router.events as any).next(navigationStart);
    expect(app.loading).toBeTrue();
  });

  it('should handle NavigationCancel event after NavigationEnd', () => {
    const router = TestBed.inject(Router);

    const navigationEnd = new NavigationEnd(1, 'some-url', 'some-url');
    (router.events as any).next(navigationEnd);
    expect(app.loading).toBeFalse();

    const navigationCancel = new NavigationCancel(2, 'some-url', 'reason');
    (router.events as any).next(navigationCancel);
    expect(app.loading).toBeFalse();
  });

  it('should handle NavigationError event after NavigationEnd', () => {
    const router = TestBed.inject(Router);

    const navigationEnd = new NavigationEnd(1, 'some-url', 'some-url');
    (router.events as any).next(navigationEnd);
    expect(app.loading).toBeFalse();

    const navigationError = new NavigationError(2, 'some-url', 'error');
    (router.events as any).next(navigationError);
    expect(app.loading).toBeFalse();
  });

  it('should handle NavigationEnd event after NavigationCancel', () => {
    const router = TestBed.inject(Router);

    const navigationCancel = new NavigationCancel(1, 'some-url', 'reason');
    (router.events as any).next(navigationCancel);
    expect(app.loading).toBeFalse();

    const navigationEnd = new NavigationEnd(2, 'some-url', 'some-url');
    (router.events as any).next(navigationEnd);
    expect(app.loading).toBeFalse();
  });

  it('should handle NavigationEnd event after NavigationError', () => {
    const router = TestBed.inject(Router);

    const navigationError = new NavigationError(1, 'some-url', 'error');
    (router.events as any).next(navigationError);
    expect(app.loading).toBeFalse();

    const navigationEnd = new NavigationEnd(2, 'some-url', 'some-url');
    (router.events as any).next(navigationEnd);
    expect(app.loading).toBeFalse();
  });

  it('should handle NavigationCancel event after NavigationError', () => {
    const router = TestBed.inject(Router);

    const navigationError = new NavigationError(1, 'some-url', 'error');
    (router.events as any).next(navigationError);
    expect(app.loading).toBeFalse();

    const navigationCancel = new NavigationCancel(2, 'some-url', 'reason');
    (router.events as any).next(navigationCancel);
    expect(app.loading).toBeFalse();
  });

  it('should handle NavigationError event after NavigationCancel', () => {
    const router = TestBed.inject(Router);

    const navigationCancel = new NavigationCancel(1, 'some-url', 'reason');
    (router.events as any).next(navigationCancel);
    expect(app.loading).toBeFalse();

    const navigationError = new NavigationError(2, 'some-url', 'error');
    (router.events as any).next(navigationError);
    expect(app.loading).toBeFalse();
  });

  it('should handle NavigationStart event after NavigationStart event', fakeAsync(() => {
    const router = TestBed.inject(Router);

    const navigationStart1 = new NavigationStart(1, 'some-url-1');
    (router.events as any).next(navigationStart1);
    expect(app.loading).toBeTrue();

    tick(); // Simulate async behavior
    const navigationStart2 = new NavigationStart(2, 'some-url-2');
    (router.events as any).next(navigationStart2);
    expect(app.loading).toBeTrue();

    tick(); // Simulate async behavior
    const navigationEnd = new NavigationEnd(3, 'some-url-2', 'some-url-2');
    (router.events as any).next(navigationEnd);
    expect(app.loading).toBeFalse();
  }));
});
