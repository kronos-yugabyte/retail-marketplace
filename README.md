# Kronos Martketplace App

App provides rest endpoints for searching the product catalog and provides results for displaying the analytics on the frontend. It uses Spring Data for exposing the data stored in YugaByte DB to downstream application. 

# Running the app

## Localhost Instructions

Step 1. Replace the `application.properties` with the following:

```
spring.profiles.active=local
cronos.yugabyte.keyspace=cronos
cronos.yugabyte.hostname=127.0.0.1
cronos.yugabyte.port=9042
```

Step 2. Create the necessary tables in YugaByte DB.

```
$ cqlsh -f schema.cql
```

Step 3. Build and run the Rest API server

```
$ cd client-apps/cronos-yugabyte-rest-api
$ mvn -DskipTests install
$ mvn spring-boot:run
```

Step 4. Build and run the React UI

```
$ cd client-apps/cronos-react-ui
$ mvn spring-boot:run
```

Now open http://localhost:8081 to view the UI.

## PCF (cloud foundry) Instructions

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

