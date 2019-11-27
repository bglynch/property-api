# Dashboard API
- ##### Maven
- ##### Spring Boot
- ##### MySQL/H2
- ##### Docker

### Running the API

---
Build the .jar file, build the docker image and run the docker image
```bash
mvn clean package
docker build -t <image name> . 
docker run -p 8080:8080 <image name>
```

Build the docker image
```java
mvn clean package docker:build  
mvn docker:run
```
Building the docker image
```bash
docker build -t bglynch/dshboard_api:0.0.1 .
docker build -t bglynch/dashboard-api:0.1 -f src/main/docker/Dockerfile .
```
Run the image in a docker container
```bash
docker run --name dashbrd-container -p 8080:8080 -d bglynch/dshboard_api:0.0.1 
```

---
#### Run Option 01: 
> Spring Boot RestAPI: Ran locally  
> Database: In memory H2 Database
```bash
mvn -Dspring.profiles.active=dev-1 spring-boot:run
```

### Run Option 02:  
> Spring Boot RestAPI: Ran locally  
> Database: MySQL DB, Ran in a Docker Container
```bash
docker run -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=dashboard -p 3307:3306 -d mysql:5.7
mvn -Dspring.profiles.active=dev-1 spring-boot:run
```


#### Run Option 03: 
> Spring Boot RestAPI: Ran in a Docker Container  
> Database: In memory H2 Database
```bash
docker build -t bglynch/dashboard-api:1.1 -f src/main/docker/Dockerfile .
docker run -p 8080:8080 -d bglynch/dashboard-api:1.1
```
##### src/main/docker/Dockerfile
```dockerfile
# choose dev-1 as the spring profile
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev-1", "-jar","/myapp.jar"]
```

#### Run Option 04: 
> Spring Boot RestAPI: Ran in a Docker Container  
> Database: MySQL DB, Ran in a Docker Container  
##### terminal $
```bash
# run a mysql image in a docker container
docker run --name dash-sqldb -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=dashboard -e MYSQL_USER=dashboard_user -e MYSQL_PASSWORD=dashboard_pass -d mysql:5.7

# build API docker image and run in a docker container linked to the mysql container
docker build -t bglynch/dashboard-api:1.3 -f src/main/docker/Dockerfile .
docker run -p 8080:8080 --name demo-app3 --link dash-sqldb:mysql bglynch/dashboard-api:1.3
```

##### src/main/docker/Dockerfile
```dockerfile
# choose dev-2 as the spring profile
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev-2", "-jar","/myapp.jar"]
```

