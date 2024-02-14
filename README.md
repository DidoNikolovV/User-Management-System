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
![main-page](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/c6986f24-aa27-4bfc-9052-96010d4e1090)

### Main Page With Search Param
![main-page-search](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/1468b0b8-0f57-4f35-b157-90f75355b4d0)

### User Details Page
![user-details](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/e31e5b43-b66c-432e-95ea-7ae5b7e1794e)

### Edit User Page
![edit-user](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/d890a456-09ee-45ec-a25b-ec86271e8bb7)

### Create User Page
![create-user](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/1147ab20-e98c-4a05-8132-fd18b3c34eae)

Explore the API using the Swagger UI.

Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

![swagger](https://github.com/DidoNikolovV/User-Management-System/assets/53466577/3fc20cff-b923-4bdf-b8c8-d800b965bb4a)


### Accessing Swagger UI

Follow these steps to access the Swagger UI for exploring the API:

1. Start your application.
2. Open a web browser.
3. Navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
4. Explore available endpoints, request parameters, and responses interactively.



 
### Prerequisites

Ensure you have the following prerequisites installed on your system:
- Java Development Kit (JDK)
   - This project requires Java 17.
- Gradle - 7.5 or higher
- IDE: IntelliJ
- MySQL

### Installation

# Clone the repository:**
   ```bash
   git clone https://github.com/DidoNikolovV/User-Management-System.git
   ```
## Navigate to the project directory
   ```bash
   cd User-Management-System
   ```
### Build and Run
 
### Open InteliJ in the project directory
   ```bash
     idea .
   ```
### **Update Application Configuration**
    Open the src/main/resources/application.yml file.

    Locate the following section and update the MySQL connection details according to your setup:

    yaml

    datasource:
     driverClassName: com.mysql.cj.jdbc.Driver
     url: jdbc:mysql://<your-database-host>:<your-database-port>/user-management-system?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true&serverTimezone=UTC
     username: <your-database-username>
     password: <your-database-password>

        Update url, username, and password as needed.

### **Run the application**
    Go to the main class, right click and run it.

The API will start running at http://localhost:8080. 
The default credentials for accessing the API are as follows:

    Username: admin
    Password: 12345
    You can now explore the endpoints using your preferred API client.
    
Dependencies
    Spring Boot: Simplifies the development of production-ready Spring applications.
    Spring Data JPA: Simplifies data access using the Java Persistence API (JPA).
    MySQL: In-memory database for development and testing.
    Thymeleaf: A modern server-side Java template engine.
