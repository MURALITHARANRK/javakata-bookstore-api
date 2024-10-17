# Online Book Store Application

This application has been developed using Java SpringBoot for online book store. Which is having multiple features. 1. Add New Book, 2. Get all the books details, 3. Order Book, 4. Keep books in Cart.

## Prerequisites

- Java JDK 11
- Maven 3.X

### Steps to run the application

1. Clone the repository to your local machine:

```bash
$ git clone https://github.com/MURALITHARANRK/javakata-bookstore-api.git
$ cd javakata-bookstore-api
```
2. Install dependencies and build the application:
```bash
$ mvn clean install
```
3. Run the application
 ```bash
$ mvn spring-boot:run
```
Application will be running on http://localhost:8080

### Swagger
Swagger API documentation is available in following link
```bash
http://localhost:8080/swagger-ui/index.html
```
### Database
H2 In-memory database used for this application, Which we can access using the below credentials

username:sa     
password:password

```
http://localhost:8080/h2-console/
```

