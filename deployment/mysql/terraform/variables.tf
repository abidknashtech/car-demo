# Google Cloud connection & authentication and Application configuration | variables.tf

# GCP authentication file
variable "gcp_auth_file" {
  type = string
  description = "GCP authentication file"
}

# define GCP region
variable "gcp_region_1" {
  type = string
  description = "GCP region"
}

# define GCP zone
variable "gcp_zone_1" {
  type = string
  description = "GCP zone"
}

# define GCP project name
variable "app_project" {
  type = string
  description = "GCP project name"
}

# define application name
variable "app_name" {
  type = string
  description = "Application name"
}

# define application environment
variable "app_environment" {
  type = string
  description = "Application environment"
}

variable "create_order_table" {
  type = string
  description = "sql ddl to create car_orders table in orders database"
}

variable "create_inventory_table" {
  type = string
  description = "sql ddl to create car_inventory table in inventory database"
}

variable "create_payment_table" {
  type = string
  description = "sql ddl to create car_payment table in payment database"
}

variable "create_shipment_table" {
  type = string
  description = "sql ddl to create car_shipment table in shipment database"
}
