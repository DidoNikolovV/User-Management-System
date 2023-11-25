# User Management REST API

User Management REST API project! This API provides a versatile CRUD (Create, Read, Update, Delete) interface for managing user data through standard HTTP methods.

## Technologies Used
- **Java**
- **Spring Boot**
- **Gradle**
- **MySQL**
- **Thymeleaf**
- **JavaScript**

## Getting Started
### Main Page
![main-page](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/ab3c6b7b-89fa-49d8-9ca9-91d4bda65ea8)

### Main Page With Search Param
![main-page-search](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/bebf7e62-431b-47ad-9f0d-2d68aa5c8447)

### Edit User Page
![edit-user](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/d890a456-09ee-45ec-a25b-ec86271e8bb7)

### Create User Page
![create-user](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/1147ab20-e98c-4a05-8132-fd18b3c34eae)

# User Management API

## Get Users

### Endpoint
   ```http
   GET /api/users
   ```

## Get User By ID

### Endpoint

   ```http
   GET /api/users/{userId}
   ```

## Create User

### Endpoint

   ```http
   POST /api/users
   ```

## Edit User

### Endpoint
   
   ```http
   PUT /api/users/edit/{userId}
```

## Delete User

### Endpoint
   
   ```http
   DELETE /api/users/{userId}
   ```

### Prerequisites

Ensure you have the following prerequisites installed on your system:
- Java Development Kit (JDK)
- Apache Maven
- IDE: IntelliJ

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/user-management-rest-api.git
   cd user-management-rest-api


2. **Build Project**
   ```bash
    mvn clean install
3. **Run the application**
   ```bash
   mvn spring-boot:run

    mvn spring-boot:run

Spring Security User Credentials

The default credentials for accessing the API are as follows:

    Username: admin
    Password: 12345

MySQL Database Connection

    Ensure you have MySQL installed on your machine.

    Open the src/main/resources/application.yml file.

    Locate the following section and update the MySQL connection details according to your setup:

    yaml

    datasource:
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/user-management-system?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true&serverTimezone=UTC
      username: root
      password:

        Update url, username, and password as needed.

    Save the changes.

The API will start running at http://localhost:8080. You can now explore the endpoints using your preferred API client or tools like Postman.
Dependencies

    Spring Boot: Simplifies the development of production-ready Spring applications.
    Spring Data JPA: Simplifies data access using the Java Persistence API (JPA).
    MySQL: In-memory database for development and testing.
    Thymeleaf: A modern server-side Java template engine.
