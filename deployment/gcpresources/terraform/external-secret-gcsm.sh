#!/bin/sh

GKE_CLUSTER="$1"
REGION="$2"
gcloud container clusters get-credentials "$GKE_CLUSTER" --region "$REGION"
kubectl create secret generic gcpsm-secret --from-file=secret-access-credentials=key.json

helm repo add external-secrets https://charts.external-secrets.io
helm install external-secrets external-secrets/external-secrets -n default  --set installCRDs=true