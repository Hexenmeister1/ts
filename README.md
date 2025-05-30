# Ticket Management System API

## Swagger UI 
http://localhost:8080/swagger-ui.html

## OpenAPI documentation
http://localhost:8080/api-docs

## How to Run
./run.sh  
or  
mvn spring-boot:run  
Access the application at http://localhost:8080  

## H2 Console
http://localhost:8080/h2-console

## API Endpoints
Method  Endpoint	                        Description  
POST    /api/tickets	                    Create a new ticket  
GET     /api/tickets	                    Get all tickets  
GET     /api/tickets/status/{status}	    Get tickets by status (OPEN/CLOSED)  
GET     /api/tickets/priority/{priority}	Get tickets by priority (LOW/MEDIUM/HIGH)  
PUT     /api/tickets/{id}	                Update a ticket  
PUT     /api/tickets/{id}/close	            Close a ticket  
DELETE  /api/tickets/{id}	                Delete a ticket  

## Generate javadoc
mvn javadoc:javadoc

## Docker
Build docker container:  
$ docker build -t ts .  
  
Run the container:  
$ docker run -p 8080:8080 ts  

Run the container with custom JVM options:  
$ docker run -p 8080:8080 -e "JAVA_OPTS=-Xmx512m" ts  