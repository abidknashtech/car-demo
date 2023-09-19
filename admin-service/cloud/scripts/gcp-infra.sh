#!/bin/bash



# Set your GCP project ID and other variables
PROJECT_ID="stellar-works-398917"
CLUSTER_NAME="cluster-gcp-test"
REGION="us-central1-c"
TOPIC_NAME="Vehicle"
DB_NAME="gcp-firestore-db"
DB_REGION="asia-south2"
DB_TYPE="firestore-native"



# Enable required APIs
gcloud services enable container.googleapis.com pubsub.googleapis.com cloudfunctions.googleapis.com firestore.googleapis.com
# Create a GKE cluster
gcloud container clusters create $CLUSTER_NAME --num-nodes=2 --region=$REGION --scopes "https://www.googleapis.com/auth/cloud-platform"
# Create a Pub/Sub topic
gcloud pubsub topics create $TOPIC_NAME --project=$PROJECT_ID
#Create Firestore DB
gcloud alpha firestore databases create --location=$DB_REGION --type=$DB_TYPE



echo "Infrastructure and services set up successfully."