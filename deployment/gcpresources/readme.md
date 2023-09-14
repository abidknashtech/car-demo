## deploy mysql on GCP using gcloud cli

### 1. Authenticate gcloud
```
install gcloud cli if not installed https://cloud.google.com/sdk/docs/install 
a) export GOOGLE_APPLICATION_CREDENTIALS=key.json
b) gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
```

### 2. Create cloud sql instance
```
gcloud sql instances create car-demo-dev-db \
--database-version=MYSQL_8_0 \
--tier=db-f1-micro \
--region=us-east1 \
--project=boreal-gravity-396810 \
--storage-type=SSD \
--storage-size=10 \
--storage-auto-increase \
--authorized-networks=0.0.0.0/0
``` 
#### To Stop the sql instance
```
gcloud sql instances patch car-demo-dev-db \
--project=boreal-gravity-396810 \
--activation-policy=NEVER
```

#### To start the sql instance
```
gcloud sql instances patch car-demo-dev-db \
--project=boreal-gravity-396810 \
--activation-policy=ALWAYS
```

#### To delete the sql instance
```gcloud sql instances delete car-demo-dev-db --project=boreal-gravity-396810```

### 3. Create mysql user to connect to instance

```
gcloud sql users create java_db_user \
--instance=car-demo-dev-db \
--project=boreal-gravity-396810 \
--password=JavaDBs#password2023
```

### 4. Create Databases
```
gcloud sql databases create order_db \
--instance=car-demo-dev-db \
--project=boreal-gravity-396810

gcloud sql databases create inventory_db \
--instance=car-demo-dev-db \
--project=boreal-gravity-396810

gcloud sql databases create payment_db,  \
--instance=car-demo-dev-db \
--project=boreal-gravity-396810

gcloud sql databases create shipment_db \
--instance=car-demo-dev-db \
--project=boreal-gravity-396810
```

### 5. create tables if needed(table creatation is done by code so no need to create manually)
change the host name with yours mysql db instance host(public ip) user and password
install python 3.8 and 
pip install mysql-connector-python
```
python create_tables.py --host 35.237.111.227 --user java_db_user --password JavaDBs#password2023 --databases order_db,inventory_db,payment_db,shipment_db
```

follow the link - https://cloud.google.com/sql/docs/mysql/create-instance 

## Deploy gcp resources through terraform

install terraform in your local

- terraform init
- terraform plan
- terraform apply

To destroy the mysql resources
- terraform destroy

