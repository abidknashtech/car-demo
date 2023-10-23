#!/bin/bash

AKS_CLUSTER="az-nashtech-cluster"
SERVICE_NAME="admin-service"
DEPLOYMENT_NAME="adminservice"
RESOURCE_GROUP_NAME="az-nashtech-resource-group"

sudo az aks install-cli
sudo az acr login --name ntdemocontainerregistry --username ntdemocontainerregistry --password NshiXQFKNlAYyXJ/p7YxsSHwYqqEZwenHpJiQbpu9I+ACRDsvZur
echo "---------build and deploy $SERVICE_NAME-----------"
cd "$SERVICE_NAME" || exit
mvn clean install
echo "---------packaging done, start docker build-----------"
sudo docker build -f Dockerfile --tag ntdemocontainerregistry.azurecr.io/"$SERVICE_NAME":"$GITHUB_SHA" .
echo  "--------docker build done, docker push---------------"
sudo docker push ntdemocontainerregistry.azurecr.io/"$SERVICE_NAME":"$GITHUB_SHA"
echo  "--------pushed docker image, deploy to aks cluster--------------------------"

sudo az login --service-principal -u 72c49877-6440-4e98-995b-42e97b099f8d -p WnM8Q~kzKjb5XAvu.Yw4f7lqeQ4H5euqauzvvaJ4 --tenant 023d14ee-6534-4891-94be-7d7350125054
echo  "--------getting kube config--------------------------"
sudo az aks get-credentials --resource-group "$RESOURCE_GROUP_NAME" --name "$AKS_CLUSTER"
# setup kustomize
echo  "--------setting up kustomize--------------------------"
curl -sfLo kustomize https://github.com/kubernetes-sigs/kustomize/releases/download/v3.1.0/kustomize_3.1.0_linux_amd64
chmod u+x ./kustomize

# set docker image for kustomize
echo  "---------------set docker image for kustomize-------------------"
./kustomize edit set image ntdemocontainerregistry.azurecr.io/IMAGE:TAG=ntdemocontainerregistry.azurecr.io/"$SERVICE_NAME":"$GITHUB_SHA"
echo "---------------deploy through kubectl------------------"
# deploy through kubectl
echo "----Home dir-----"
echo $HOME
mkdir -p $HOME/.kube
sudo cp -i /root/.kube/config $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
./kustomize build . | kubectl apply -f -
kubectl rollout status deployment/"$DEPLOYMENT_NAME"
kubectl get services -o wide
echo "-------------$SERVICE_NAME deployed on $CLUSTER_NAME----------"

