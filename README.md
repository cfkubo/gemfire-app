# gemfire-app

This is a simple Spring Boot application demonstrating basic PUT and GET operations with VMware Tanzu GemFire using Spring Data for Tanzu GemFire. It exposes a REST API to store and retrieve Person objects in a GemFire distributed cache.

Important Note: As of Spring Data for Tanzu GemFire 2.0, the client application cannot configure or start GemFire servers. A GemFire server instance must be running and accessible before this client application is started.


Prerequisites
Before running this application, ensure you have the following installed:

Java Development Kit (JDK) 17 or later

Apache Maven 3.6.0 or later

VMware Tanzu GemFire 10.1.x (Download from Broadcom Customer Support Portal)

This includes the gfsh command-line tool.

1. GemFire Server Setup
You must start a GemFire cluster (at least one locator and one server) and create the necessary region before starting the Spring Boot client application.

Start gfsh:
Open a terminal and navigate to your VMware Tanzu GemFire installation directory (e.g., ~/gemfire-10.1.2).

./bin/gfsh

Start a Locator:
This command starts a GemFire Locator instance.

start locator --name=locator1 --port=10334

Start a Server:
This command starts a GemFire Server instance and connects it to the locator.

start server --name=server1 --server-port=40404 --locators=localhost[10334]

Create the GemFire Region:
The Person entity in the Spring Boot application maps to a GemFire region named People. You need to create this region on your GemFire server.

create region --name=People --type=PARTITION

You should see confirmation that the region was created on server1.

Verify GemFire Cluster Status (Optional):

list members
list regions

This confirms locator1 and server1 are running, and the /People region exists.

2. Maven settings.xml Configuration
To download VMware Tanzu GemFire dependencies from the Broadcom Artifactory, you need to configure your Maven settings.xml file.

Obtain Access Token:

Log in to the Broadcom Customer Support Portal.

Go to the VMware Tanzu GemFire downloads page.

Find "Click Green Token for Repository Access" and click the Token Download icon.

Copy the Access Token provided.

Edit settings.xml:
Locate your Maven settings.xml file (typically in ~/.m2/settings.xml). If it doesn't exist, create it. Add the following <servers> block, replacing EXAMPLE-USERNAME with your Broadcom Support Portal username and MY-PASSWORD with the Access Token you copied.

<!-- ~/.m2/settings.xml -->
<settings>
    <servers>
        <server>
            <id>gemfire-release-repo</id>
            <username>EXAMPLE-USERNAME</username>
            <password>MY-PASSWORD</password>
        </server>
    </servers>
</settings>

3. Building the Application
Navigate to the root directory of the my-gemfire-app project (where pom.xml is located) in your terminal and execute the Maven clean install command:

mvn clean install

This will download dependencies, compile the code, and package the application.

4. Running the Application Locally
After building the application and ensuring your GemFire server is running with the /People region created:

mvn spring-boot:run

The application will start, typically on http://localhost:8080. You will see Spring Boot logs indicating successful startup and connection to GemFire.

5. Interacting with the Application (API Endpoints)
Once the Spring Boot application is running, you can interact with its REST API using curl or any API client (like Postman, Insomnia).

PUT (Create/Update a Person)
This endpoint allows you to store or update a Person object in the GemFire /People region.

curl -X PUT -H "Content-Type: application/json" -d '{"id": "1", "name": "Alice", "age": 30}' http://localhost:8080/persons

Expected Response (HTTP 200 OK):

{"id":"1","name":"Alice","age":30}

GET (Retrieve a Person by ID)
This endpoint retrieves a Person object from the GemFire /People region using its ID.

curl http://localhost:8080/persons/1

Expected Response (HTTP 200 OK if found):

{"id":"1","name":"Alice","age":30}

Expected Response (HTTP 404 Not Found if not found):

# (No JSON body for 404, usually)

6. Deploying to Cloud Foundry 
Deploying a Spring Boot application with VMware Tanzu GemFire to Cloud Foundry typically involves:

Creating a GemFire Service Instance:
You would provision a managed VMware Tanzu GemFire service instance in your Cloud Foundry environment. The exact command depends on your Cloud Foundry service broker for GemFire.

cf create-service <gemfire-service-broker> <plan-name> my-gemfire-service

Pushing the Application:
Push your Spring Boot application to Cloud Foundry. The manifest.yml file is commonly used for configuration.


Then, push the application:

cf push

Creating Regions on the Cloud Foundry GemFire Cluster:
After your GemFire service is provisioned, you would connect to it (e.g., via a jumpbox, gfsh access) and create the /People region on that cluster, similar to the local setup instructions.

Interacting with the Deployed Application:
Once deployed, you would use the URL provided by Cloud Foundry for your application (e.g., http://my-gemfire-app.<your-cf-domain>/persons) to interact with the API.


```
create region --name=People --type=PARTITION
```

```
curl -X PUT -H "Content-Type: application/json" -d '{"id": "1", "name": "Alice", "age": 30}' http://localhost:8080/persons\
```

```
 curl http://localhost:8080/persons/1
```           


