import { TestBed } from "@angular/core/testing";
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { CartService } from "./cart.service";

describe("CartService", () => {
  let service: CartService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(CartService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });

  it("should get cart items", () => {
    const mockCartItems = [{
      productId: "1",
      brand: "Brand",
      model: "Model",
      color: "Red",
      tax: "0.1",
      basePrice: "100",
      quantity: "2",
    }];
    service.getCartItems().subscribe((cartItems) => {
      expect(cartItems).toEqual(mockCartItems);
    });

    const req = httpTestingController.expectOne("http://localhost:9094/cart/get?userId=1652");
    expect(req.request.method).toEqual("GET");

    req.flush(mockCartItems);
  });

  it("should remove from cart", () => {
    const productId = "123";
    const quantity = 1;
    const userId = "1652";

    service.removeFromCart(productId, quantity, userId).subscribe((response) => {
      // Add expectations for the response if needed
    });

    const req = httpTestingController.expectOne(`http://localhost:9094/cart/remove?productId=${productId}&quantity=${quantity}&userId=1652`);
    expect(req.request.method).toEqual("POST");

    req.flush({ /* mocked response data */ });
  });

  it("should add to cart", () => {
    const carId = "456";

    service.addToCart(carId).subscribe((response) => {
      // Add expectations for the response if needed
    });

    const req = httpTestingController.expectOne(`http://localhost:9094/cart/add?productId=${carId}&quantity=1&userId=1652`);
    expect(req.request.method).toEqual("POST");

    req.flush({ /* mocked response data */ });
  });

  it("should search all cars", () => {
    const mockCars = [{ /* mocked car data */ }];

    service.searchAllCars().subscribe((cars) => {
      expect(cars).toEqual(mockCars);
    });

    const req = httpTestingController.expectOne("http://localhost:8080/apis/car/all");
    expect(req.request.method).toEqual("GET");

    req.flush(mockCars);
  });

  it("should search cars by ID", () => {
    const carId = "789";
    const mockCar = { /* mocked car data */ };

    service.searchCarsById(carId).subscribe((car) => {
      expect(car).toEqual(mockCar);
    });

    const req = httpTestingController.expectOne(`http://localhost:8080/apis/car/byId/${carId}`);
    expect(req.request.method).toEqual("GET");

    req.flush(mockCar);
  });

  it("should search cars by mileage", () => {
    const mileage = "50000";
    const mockCars = [{ /* mocked car data */ }];

    service.searchCarsByMileage(mileage).subscribe((cars) => {
      expect(cars).toEqual(mockCars);
    });

    const req = httpTestingController.expectOne(`http://localhost:8080/apis/car/byMileage/${mileage}`);
    expect(req.request.method).toEqual("GET");

    req.flush(mockCars);
  });

  it("should search cars by brand", () => {
    const brand = "Toyota";
    const mockCars = [{ /* mocked car data */ }];

    service.searchCarsByBrand(brand).subscribe((cars) => {
      expect(cars).toEqual(mockCars);
    });

    const req = httpTestingController.expectOne(`http://localhost:8080/apis/car/byBrand/${brand}`);
    expect(req.request.method).toEqual("GET");

    req.flush(mockCars);
  });

  it("should search cars by price", () => {
    const price = "20000";
    const mockCars = [{ /* mocked car data */ }];

    service.searchCarsByPrice(price).subscribe((cars) => {
      expect(cars).toEqual(mockCars);
    });

    const req = httpTestingController.expectOne(`http://localhost:8080/apis/car/byPrice/${price}`);
    expect(req.request.method).toEqual("GET");

    req.flush(mockCars);
  });

  it("should get all orders", () => {
    const mockOrders = [{ /* mocked order data */ }];

    service.getAllOrders().subscribe((orders) => {
      expect(orders).toEqual(mockOrders);
    });

    const req = httpTestingController.expectOne("http://localhost:9090/orders/1652");
    expect(req.request.method).toEqual("GET");

    req.flush(mockOrders);
  });
});
