## Deploy Axon server on GKE

### 1. Install gcloud sdk and gcloud component kubectl
```
curl https://sdk.cloud.google.com | bash (Authenticate with service account json key)
exec -l $SHELL
gcloud init
gcloud components install kubectl
```

### 2. Create GKE cluster
```
gcloud container clusters create-auto axonserver-gke \
--project=boreal-gravity-396810 \
--location=us-east1
```

### deploy axonserver application resource to cluster
```
 kubectl apply -f axonserver/cli/deployment.yaml

```

### deploy a service to get single point of access to a set of Pods(network, load balancer)
```
kubectl apply -f axonserver/cli/service.yaml
```

#### to check the pod running
```
kubectl get pods
```
#### to Get the external IP address of the service
```
kubectl get services
```

### Delete the gke cluster
```
gcloud container clusters delete axonserver-gke \
    --location us-east1
```