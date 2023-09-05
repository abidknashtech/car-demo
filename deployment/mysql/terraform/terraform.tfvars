# Application Definition 
app_name        = "car-demo"
app_environment = "dev"
app_project     = "boreal-gravity-396810"

# GCP Settings
gcp_region_1  = "us-east1"
gcp_zone_1    = "us-east1-b"
gcp_auth_file = "key.json"

# SQL DDLs
create_order_table = "CREATE TABLE car_orders (orderId VARCHAR(255) PRIMARY KEY, productId VARCHAR(255), addressId VARCHAR(255), userId VARCHAR(255), orderStatus VARCHAR(255), quantity INT)"

create_inventory_table = "CREATE TABLE car_inventory(productId VARCHAR(255) PRIMARY KEY, name VARCHAR(255), price VARCHAR(255), quantity INT);"

create_payment_table = "CREATE TABLE car_payment(paymentId VARCHAR(255) PRIMARY KEY, orderId VARCHAR(255), paymentStatus VARCHAR(255), timeStamp DATE);"

create_shipment_table = "CREATE TABLE car_Shipment(shipmentId VARCHAR(255) PRIMARY KEY, orderId VARCHAR(255), shipmentStatus VARCHAR(255));"