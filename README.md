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

# User Management API

## Get Users

### Endpoint
   ```http
   GET /api/users
   ```
   ### Example Request:
   GET /api/users
    
   ### Example Response:
   ```json
   [
     {
       "userId": 1,
       "firstName": "Dido",
       "lastName": "Nikolov",
       "dateOfBirth": "2023-11-25",
       "phoneNumber": "0896201921",
       "email": "dido@gmail.com"
     },
      // Additional user objects...
   ]
   ```


## Get User By ID

### Endpoint

   ```http
   GET /api/users/{userId}
   ```
### Example Request:
   ```http
   GET /api/users/2
   ```
### Example Response:
   ```json
   {
      "id":2,
      "firstName":"Pesho",
      "lastName":"Ivanov",
      "dateOfBirth":"2023-11-25",
      "phoneNumber":"0884201973",
      "email":"pesho@abv.bg"
   }
   ```

## Get Users with Pagination

### Endpoint
   ```http
   GET /api/users
   ```
### Example Request:
   ```http
   GET /api/users?page=1&size=3
   ```

### Example Response:
  ```json
      [
         {
            "id":1,
            "firstName":"Dido",
            "lastName":"Nikolov",
            "dateOfBirth":"2023-11-25",
            "phoneNumber":"0896201921",
            "email":"dido@gmail.com"
         }
      ]
   ```

## Create User

### Endpoint

   ```http
   POST /api/users
   ```
### Example Request:
   ```http
   POST /api/users
   ```
   ```json
   {
      "firstName":"test",
      "lastName":"test",
      "dateOfBirth":"2019-09-25",
      "phoneNumber":"088123321",
      "email":"test@abv.bg"
   }
   ```
### Example Response:
   ```json
   {
      "id":3,
      "firstName":"test",
      "lastName":"test",
      "dateOfBirth":"2019-09-25",
      "phoneNumber":"088123321",
      "email":"test@abv.bg"
   }
   ```

## Edit User

### Endpoint
   
   ```http
   PUT /api/users/edit/{userId}
   ```
### Example Request:
   ```http
   PUT /api/users/edit/1
   ```
   ```json
      {
        "firstName": "DidoUpdated",
        "lastName": "Nikolov",
        "dateOfBirth":"2019-09-25",
        "phoneNumber":"0896201921",
          "email":"dido@abv.bg"
      }
   ```
### Example Response:
   ```json
   {
      "id":1,
      "firstName":"DidoUpdated",
      "lastName":"Nikolov",
      "dateOfBirth":"2019-09-25",
      "phoneNumber":"0896201921",
      "email":"dido@abv.bg"
   }
   ```

## Delete User

### Endpoint
   
   ```http
   DELETE /api/users/{userId}
   ```
### Example Request:
   ```http
   GET /api/users/2
   ```
### Example Response:
   ```json
   {
      "id":2,
      "firstName":"Pesho",
      "lastName":"Ivanov",
      "dateOfBirth":"2023-11-25",
      "phoneNumber":"0884201973",
      "email":"pesho@abv.bg"
   }
   ```

### Example Response:
   ```json
   {
      "id":2,
      "firstName":"Pesho",
      "lastName":"Ivanov",
      "dateOfBirth":"2023-11-25",
      "phoneNumber":"0884201973",
      "email":"pesho@abv.bg"
   }
   ```

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
### Windows
 
### Build the project:
   ```bash
   gradlew.bat build --warning-mode all
   ```
### Run the Project
   ```bash
   gradlew.bat bootRun --warning-mode all
   ```

MySQL Database Connection
   1. **Install MySQL:**
      Ensure you have MySQL installed on your machine. You can download MySQL from [here](https://dev.mysql.com/downloads/).
   
   2. **Create Database:**
      Open your MySQL client (e.g., MySQL Workbench).
   
   ### Update Application Configuration

    Open the src/main/resources/application.yml file.

    Locate the following section and update the MySQL connection details according to your setup:

    yaml

    datasource:
     driverClassName: com.mysql.cj.jdbc.Driver
     url: jdbc:mysql://<your-database-host>:<your-database-port>/user-management-system?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true&serverTimezone=UTC
     username: <your-database-username>
     password: <your-database-password>

        Update url, username, and password as needed.

    Save the changes.
   

3. **Run the application**

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
