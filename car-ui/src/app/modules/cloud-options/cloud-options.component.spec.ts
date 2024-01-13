import { ComponentFixture, TestBed } from "@angular/core/testing";
import { CloudOptionsComponent } from "./cloud-options.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { RouterTestingModule } from "@angular/router/testing";
import { MaterialModule } from "../../shared/module/material.module";

describe("CloudOptionsComponent", () => {
  let component: CloudOptionsComponent;
  let fixture: ComponentFixture<CloudOptionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CloudOptionsComponent],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [HttpClientModule, RouterTestingModule, MaterialModule],
    });
    fixture = TestBed.createComponent(CloudOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
