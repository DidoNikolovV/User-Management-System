# User Management REST API

Welcome to the User Management REST API project! This API provides a versatile CRUD (Create, Read, Update, Delete) interface for managing user data through standard HTTP methods. Whether you're building an employee management system, a customer database, or any application requiring user-related functionalities, this project offers a robust and flexible solution.

## Technologies Used
- **Java**
- **Spring Boot**
- **Gradle**
- **MySQL**
- **Thymeleaf**
- **JavaScript**

## Getting Started
### Main Page
![main-page](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/37e8edaf-304d-4423-9fa1-03f8aab23bbd)

### Main Page With Search Param
![main-page-search](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/b31e3b21-3b14-4593-8f00-2ce7210c2257)

### Edit User Page
![edit-user](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/f3c4b440-feba-4348-89d8-a2f54b156397)

### Create User Page
![create-user](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/d6740a40-19c4-4feb-aa83-f343325b13a6)

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
