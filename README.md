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


### Follow the below steps to access endpoints

Use the following credential to generate JWT Token

username: user1<br/>
password: password

**Endpoint:** **GET** /api/login<br/>
**Authorization**: Basic Auth (use the given username and password)<br/>
**Response:** Token will be generated
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjpbXSwiZXhwIjoxNzI5MjExODkwLCJpYXQiOjE3MjkyMDU4OTB9.9mndQw-S27GEhqdbQSN6Jdaox8ULpp7gcx1W6SmVt28
```
### Get all the books
**Endpoint:** **GET** /api/book<br/>
**Authorization**: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjpbXSwiZXhwIjoxNzI5MjExODkwLCJpYXQiOjE3MjkyMDU4OTB9.9mndQw-S27GEhqdbQSN6Jdaox8ULpp7gcx1W6SmVt28<br/>
**Response:** 
```
[
    {
        "id": 1,
        "title": "Head First Java",
        "author": "Kathy Sierra",
        "price": 200
    },
    {
        "id": 2,
        "title": "The Pragmatic Programmer",
        "author": "Andrew Hunt",
        "price": 190
    },
    {
        "id": 3,
        "title": "Clean Code",
        "author": "Robert C. Martin",
        "price": 180
    }
]
```
### Add book
**Endpoint:** **POST** /api/book<br/>
**Authorization**: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjpbXSwiZXhwIjoxNzI5MjExODkwLCJpYXQiOjE3MjkyMDU4OTB9.9mndQw-S27GEhqdbQSN6Jdaox8ULpp7gcx1W6SmVt28<br/>
**Request Body:**
```
{
    "title": "NewBook",
    "author": "NewBookAuthor",
    "price": 150
}
```

### Add book to Cart
**Endpoint:** **POST** /api/cart/user1<br/>
**Authorization**: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjpbXSwiZXhwIjoxNzI5MjExODkwLCJpYXQiOjE3MjkyMDU4OTB9.9mndQw-S27GEhqdbQSN6Jdaox8ULpp7gcx1W6SmVt28<br/>
**Request Body:**
```
[
    {
        "bookId": 8,
        "quantity": 4
    },
    {
        "bookId": 1,
        "quantity": 4
    }
]
```
**Response:**
```
{
    "username": "user2",
    "cartItems": [
        {
            "bookId": "8",
            "quantity": 4
        },
        {
            "bookId": "1",
            "quantity": 4
        }
    ]
]
```
