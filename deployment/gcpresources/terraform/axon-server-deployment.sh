#!/bin/sh

GKE_CLUSTER="$1"
REGION="$2"
# Authenticate axon-server-gke
gcloud container clusters get-credentials "$GKE_CLUSTER" --region "$REGION" --project "datamesh-2"

kubectl apply -f axon-server-deployment.yaml