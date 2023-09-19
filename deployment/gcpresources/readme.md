
## Deploy gcp resources through terraform

### 1. Authenticate gcloud
```
install gcloud cli if not installed https://cloud.google.com/sdk/docs/install 
a) export GOOGLE_APPLICATION_CREDENTIALS=key.json
b) gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
```

### 2. install 
  a) terraform in your local
  https://askubuntu.com/questions/983351/how-to-install-terraform-in-ubuntu
  b) install gcloud kubectl
  https://cloud.google.com/kubernetes-engine/docs/how-to/cluster-access-for-kubectl#install_kubectl
  c) install helm 
   ```snap install helm --classic```
  d) keep the service account json key (key.json) inside terraform directory and it should have the permission
   ```
    Cloud Datastore Owner
    Cloud SQL Admin
    Compute Admin
    Container Registry Service Agent
    Create Service Accounts
    Kubernetes Engine Admin
    Pub/Sub Admin
    Secret Manager Admin
    Secret Manager Secret Accessor
    Security Reviewer
    Service Account Admin
    Service Account Key Admin
    Service Account User
    Storage Admin
    Storage Object Admin
    View Service Accounts
   ```
 e) check the [terraform.tfvars](terraform%2Fterraform.tfvars) file for the gcp project and credentials values and path.

### 3. run -

- terraform init
- terraform plan
- terraform apply

To destroy the all gcp resources
- terraform destroy
