# Car Demo

## To deploy the GCP infrastructure required, follow the steps in deployment readme: 
[readme.md](deployment%2Fgcpresources%2Freadme.md)deployment/gcpresources/readme.md

 Admin service use a cloud function result, so deploy the cloud function by following the step:

 Deploy the cloud function (repo link provided): https://github.com/SahilBabbar12/googlecloudfunction.git
 1. Clone the repository on your local.
 2. Clean and install the application.
 3. Navigate to the project in the terminal.
 4. Deploy the cloud function using the specified Google Cloud command.
  ```
gcloud functions deploy cloud_function_name --gen2 --runtime=java17 --region=us-east1 --source=. --entry-point=com.knoldus.cloudfunction.PubSubDataHandler --memory=512MB --trigger-topic=Vehicle
```

## deploy services
add required secrets in https://github.com/NashTech-Labs/car-demo/settings/secrets/actions
a) GKE_KEY : the service account json key having these permission:
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
b) GKE_PROJECT: Project name
github workflow for ci cd can be found here:
[car-demo-pipeline.yml](.github%2Fworkflows%2Fcar-demo-pipeline.yml)

keep the projects name in [projects-changes-deploy.txt](projects-changes-deploy.txt) which you want to deploy.

when a commit push to main branch it will start the deployment of the services/project mentioned in 
file projects-changes-deploy.txt.
deployments steps for each project are mentioned in [apps-deployment-script.sh](apps-deployment-script.sh)

### NOTE: 
common : it consists of common utility, events, commands and models used across multiple projects.   
   you will find the deployment step of common in [apps-deployment-script.sh](apps-deployment-script.sh) 
   it will do clean package and push the package on github.
   so, Each time you make the changes in common upgrade the version in its pom.xml and also
   update same version of common in each projects pom.xml
