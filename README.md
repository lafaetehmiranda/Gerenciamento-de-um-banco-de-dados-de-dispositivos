# Device Manager API
This project implements a RESTful API for managing devices (Device Manager). It allows you to add, list, update, delete and search for devices by brand. The application uses Spring Boot and Spring Data JPA with an H2 in-memory database for persistence.

Features Add a new device Search for a device by its ID List all devices Update a device (fully or partially) Delete a device Search for devices by brand Technologies Used Java 17 (or higher) Spring Boot 2.x Spring Data JPA H2 Database (in-memory database) Maven for dependency management Installation and Configuration Prerequisites JDK 17 or higher installed Maven installed Steps to run the application: Clone the repository:

bash Copy code git clone https://github.com/your-user/device-manager.git Access the project directory:

bash Copy code cd device-manager Compile and run the project using Maven:

bash Copy code mvn spring-boot:run Access the H2 console to view the database:

URL: http://localhost:8080/h2-console JDBC URL: jdbc:h2:mem:testdb Username: sa Password: password
