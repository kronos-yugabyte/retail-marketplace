# Kronos Martketplace App

App provides rest endpoints for searching the product catalog and provides results for displaying the analytics on the frontend. It uses Spring Data for exposing the data stored in YugaByte DB to downstream application. 

## Running the app

### Prerequisites

1. Access to PCF environment
2. YugaByte DB StatefulSet running in PKS
3. IP address of LoadBalencer/Nodeport exposing the YugaByte DB service

### Deploying the app to PCF

Step 1: Build the Spring Boot application 

```
mvn clean install -DskipTests
```

Step 2: Deploy the app onto PCF

```
cf push
```

## Building and Running Locally

Step 1. Build and run the React UI

```
$ cd client-apps/cronos-react-ui
$ mvn spring-boot:run
```

Now open http://localhost:8081 to view the UI.