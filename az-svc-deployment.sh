#!/bin/bash

AKS_CLUSTER="ntdemocluster"
SERVICE_NAME="admin-service"
DEPLOYMENT_NAME="admin-service"
RESOURCE_GROUP_NAME="az-nashtech-resource-group"

sudo az aks install-cli
sudo az acr login --name ntdemocontainerregistry --username ntdemocontainerregistry --password BjGQNe5mzQquXBcYe6rh6A5aAMhDtUY5x5zs8TCzPX+ACRCQa/EP
echo "---------build and deploy $SERVICE_NAME-----------"
cd "$SERVICE_NAME" || exit
mvn clean install
echo "---------packaging done, start docker build-----------"
sudo docker build -f Dockerfile --tag ntdemocontainerregistry.azurecr.io/"$SERVICE_NAME":"$GITHUB_SHA" .
echo  "--------docker build done, docker push---------------"
sudo docker push ntdemocontainerregistry.azurecr.io/"$SERVICE_NAME":"$GITHUB_SHA"
echo  "--------pushed docker image, deploy to aks cluster--------------------------"

sudo az login --service-principal -u 4535b4bf-5f2a-4d46-9f61-f5889ba6592a -p A1q8Q~tLfdWmlKUamBRCOWa3OwZIcJ6Sia6uHb.w --tenant 17742d94-229e-4be7-b2a9-75ba757f345b
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

