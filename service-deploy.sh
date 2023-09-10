#!/bin/bash

PROJECT_ID="$(gcloud config get-value project)"
gcloud components install gke-gcloud-auth-plugin
for project in $(cat projects-deploy.txt)
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
     cd order-service || exit
     mvn clean install
     echo "---------packaging done, start docker build-----------"
     docker build -f Dockerfile --tag gcr.io/"$PROJECT_ID"/orderservice:"$GITHUB_SHA" .
     echo  "--------docker build done, docker push---------------"
     docker push gcr.io/"$PROJECT_ID"/orderservice:"$GITHUB_SHA"
     echo  "--------pushed docker image, deploy to gke cluster--------------------------"

      gcloud container clusters get-credentials "order-service-gke" --region us-east1
      # setup kustomize
      curl -sfLo kustomize https://github.com/kubernetes-sigs/kustomize/releases/download/v3.1.0/kustomize_3.1.0_linux_amd64
      chmod u+x ./kustomize

      # set docker image for kustomize
     ./kustomize edit set image gcr.io/PROJECT_ID/IMAGE:TAG=gcr.io/"$PROJECT_ID"/orderservice:"$GITHUB_SHA"
     # deploy through kubectl
     ./kustomize build . | kubectl apply -f -
      kubectl rollout status deployment/orderservice
      kubectl get services -o wide
  esac

done