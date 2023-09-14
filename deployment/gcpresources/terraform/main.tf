#Terraform plugin for creating random ids
resource "random_id" "instance_id" {
 byte_length = 4
}
#----------------------------------------my-sql-----------------------------
# create My SQL database instance
resource "google_sql_database_instance" "my_sql" {
  name                 = "${var.app_name}-${var.app_environment}-db-${random_id.instance_id.hex}"
  project              = var.app_project
  region               = var.gcp_region_1
  database_version     = var.db_version

  settings {
    tier = var.db_tier
    activation_policy           = var.db_activation_policy
    disk_autoresize             = var.db_disk_autoresize
    disk_size                   = var.db_disk_size
    disk_type                   = var.db_disk_type

    location_preference {
      zone = var.gcp_zone_1
    }

    ip_configuration {
      ipv4_enabled = "true"
      authorized_networks {
        value = var.db_instance_access_cidr
      }
    }
  }
  deletion_protection = "false"
}

# create order database
resource "google_sql_database" "order_db" {
  name      = var.orders_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}

# Create inventory database
resource "google_sql_database" "inventory_db" {
  name      = var.inventory_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}

# Create payment database
resource "google_sql_database" "payment_db" {
  name      = var.payment_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}

# Create shipment database
resource "google_sql_database" "shipment_db" {
  name      = var.shipment_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}

# create user
resource "random_id" "user_password" {
  byte_length = 8
}

resource "google_sql_user" "my_sql_user" {
  name     = var.user_name
  project  = var.app_project
  instance = google_sql_database_instance.my_sql.name
  password = var.user_password == "" ? random_id.user_password.hex : var.user_password
}

#-----------------------pub sub--------------------------------
resource "google_pubsub_topic" "shipment-notification" {
  name = "shipment-notification"
  message_retention_duration = "604800s"
}

#-----------------------GKE Cluster for applications----------------------------
resource "google_container_cluster" "car-demo-gke" {
  name     = "car-demo-gke"
  location = var.gcp_region_1
  ip_allocation_policy {
    cluster_ipv4_cidr_block  = ""
    services_ipv4_cidr_block = ""
  }
  enable_autopilot = true

}

resource "null_resource" "external-secret-car-demo-gke" {
  provisioner "local-exec" {
    command = "/bin/bash external-secret-gcsm.sh car-demo-gke ${var.gcp_region_1}"
  }
  depends_on = [google_container_cluster.car-demo-gke]
}


# use null resources to create my sql tables if needed

#resource "null_resource" "create_order_table" {
#  provisioner "local-exec" {
#    command = "python create_tables.py  ${google_sql_database_instance.my_sql.ip_address.0.ip_address} ${var.user_name} ${var.user_password} ${var.orders_db_name} '${var.create_order_table}'"
#  }
#  depends_on = [google_sql_database.order_db, google_sql_database_instance.my_sql, random_id.user_password, random_id.instance_id, google_sql_user.my_sql_user]
#}
#
#resource "null_resource" "create_inventory_table" {
#  provisioner "local-exec" {
#    command = "python create_tables.py  ${google_sql_database_instance.my_sql.ip_address.0.ip_address} ${var.user_name} ${var.user_password} ${var.inventory_db_name} '${var.create_inventory_table}'"
#  }
#  depends_on = [google_sql_database.inventory_db, google_sql_database_instance.my_sql, random_id.user_password, random_id.instance_id, google_sql_user.my_sql_user]
#}
#
#resource "null_resource" "create_payment_table" {
#  provisioner "local-exec" {
#    command = "python create_tables.py  ${google_sql_database_instance.my_sql.ip_address.0.ip_address} ${var.user_name} ${var.user_password} ${var.payment_db_name} '${var.create_payment_table}'"
#  }
#  depends_on = [google_sql_database.payment_db, google_sql_database_instance.my_sql, random_id.user_password, random_id.instance_id, google_sql_user.my_sql_user]
#}
#
#resource "null_resource" "create_shipment_table" {
#  provisioner "local-exec" {
#    command = "python create_tables.py  ${google_sql_database_instance.my_sql.ip_address.0.ip_address} ${var.user_name} ${var.user_password} ${var.shipment_db_name} '${var.create_shipment_table}'"
#  }
#  depends_on = [google_sql_database.shipment_db, google_sql_database_instance.my_sql, random_id.user_password, random_id.instance_id, google_sql_user.my_sql_user]
#}
