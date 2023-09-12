#!/bin/bash

PROJECT_ID="$(gcloud config get-value project)"
REGION="$1"
SA="$2"
echo "$SA"
# install gke-gcloud-auth-plugin to install kubectl and authenticate gke.
gcloud components install gke-gcloud-auth-plugin
kubectl create secret generic gcpsm-secret --from-file=secret-access-credentials="$SA"

build_and_deploy_service(){

   SERVICE_NAME=$1
   CLUSTER_NAME=$2
   DEPLOYMENT_NAME=$3
   echo "---------build and deploy $SERVICE_NAME-----------"
   cd "$SERVICE_NAME" || exit
   mvn clean install
   echo "---------packaging done, start docker build-----------"
   docker build -f Dockerfile --tag gcr.io/"$PROJECT_ID"/"$SERVICE_NAME":"$GITHUB_SHA" .
   echo  "--------docker build done, docker push---------------"
   docker push gcr.io/"$PROJECT_ID"/"$SERVICE_NAME":"$GITHUB_SHA"
   echo  "--------pushed docker image, deploy to gke cluster--------------------------"

    gcloud container clusters get-credentials "$CLUSTER_NAME" --region "$REGION"
    # setup kustomize
    curl -sfLo kustomize https://github.com/kubernetes-sigs/kustomize/releases/download/v3.1.0/kustomize_3.1.0_linux_amd64
    chmod u+x ./kustomize

    # set docker image for kustomize
   ./kustomize edit set image gcr.io/PROJECT_ID/IMAGE:TAG=gcr.io/"$PROJECT_ID"/"$SERVICE_NAME":"$GITHUB_SHA"
   # deploy through kubectl
   ./kustomize build . | kubectl apply -f kuberesources/
    kubectl rollout status deployment/"$DEPLOYMENT_NAME"
    kubectl get services -o wide
    echo "-------------$SERVICE_NAME deployed on $CLUSTER_NAME----------"
}


for project in $(cat projects-changes-deploy.txt)
do
   :
  case $project in
  # case 1 build and deploy package common
  "common")
    cd common || exit
    mvn -B clean deploy --file pom.xml
    cd ..;;

  # case 2 build and deploy order-service
  "order-service")
    build_and_deploy_service order-service order-service-gke orderservice
    cd ..;;

  # case 3 build and deploy inventory-service
  "inventory-service")
    build_and_deploy_service inventory-service inventory-service-gke inventoryservice
    cd ..;;

  # case 4 build and deploy payment-service
  "payment-service")
    build_and_deploy_service payment-service payment-service-gke paymentservice
    cd ..;;

  # case 5 build and deploy order-service
  "shipment-service")
    build_and_deploy_service inventory-service inventory-service-gke inventoryservice
    cd ..;;

  esac

done
