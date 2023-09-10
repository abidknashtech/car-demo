#!/bin/bash

PROJECT_ID="$(gcloud config get-value project)"
echo "$1"
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
     cd order-service || continue
     mvn clean install || continue
     gcloud auth configure-docker
     echo "---------packaging done, start docker build-----------"
     sudo docker build --tag gcr.io/$PROJECT_ID/orderservice:1.0.0 . || continue
     echo  "--------docker build done, docker push---------------"
     sudo docker push gcr.io/$PROJECT_ID/orderservice:1.0.0
     echo  "--------pushed docker image--------------------------"

  esac

done