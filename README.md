# Spring Boot Multi-Service Project

This project is a small-scale demonstration of a microservices architecture using Spring Boot. It consists of the following services:

1. **student-service**
2. **school-service**
3. **auth-service**
4. **api-gateway**
5. **eureka-server**

Each service serves a specific purpose and communicates with others to demonstrate key microservices concepts such as service discovery, API routing, and scalability.

---

## Running the Project

### Start Eureka Server
1. Navigate to the `eureka-server` directory:
   ```bash
   cd eureka-server
   ```
2. Run the service:
   ```bash
   mvn spring-boot:run
   ```

### Start Other Services
1. Start the `student-service`, `school-service`, `api-gateway` and `auth-service` in the same way:
   ```bash
   cd <service-directory>
   mvn spring-boot:run
   ```

### Running Multiple Instances
To test multiple instances of a service, you can specify a different port using the following command:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=<port-number>"
```
For example, to start a second instance of `student-service` on port 9002:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9002"
```

---

## API Endpoints

### Student-Service
- `GET /students`: Fetch all students
- `GET /students/{id}`: Fetch student by ID
- `POST /students`: Add a new student
-  GET /students/{id}/with-school`: Fetch school of a student

### School-Service
- `GET /schools`: Fetch all schools
- `GET /schools/{id}`: Fetch school by ID

### API Gateway
Routes requests to the appropriate service. For example:
- `/students` -> `student-service`
- `/schools` -> `school-service`

---

## Configuration

### Eureka Server
The `eureka-server` has configuration files to manage service discovery settings. Ensure each service has the correct `application.yml` for Eureka registration:

### API Gateway
The gateway configuration in `application.yml` specifies routing rules:
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: student-service
          uri: lb://student-service
          predicates:
            - Path=/api/students/**
        - id: school-service
          uri: lb://school-service
          predicates:
            - Path=/api/schools/**
```
## Testing

Use Postman or cURL to test the APIs. Example request to fetch all students through the API Gateway:
```bash
curl http://localhost:8080/students
```