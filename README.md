# BankX Application

This project implements a simple banking application with features like customer onboarding, account management, and payments.

## Technologies Used

* **Spring Boot:** Framework for building stand-alone, production-ready Spring applications.
* **Spring Data JPA:** Simplifies data access with JPA.
* **H2 Database:** In-memory database used for development and testing.
* **Maven:** Build tool for managing dependencies and building the project.
* **HTML, CSS, JavaScript:** Technologies used for the frontend user interface.
* **Axios:** JavaScript library for making HTTP requests to the backend.
* **Docker:** Containerization technology for packaging and running the application.

## Project Structure

```
BankApplication/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── bankx/
│   │   │   │       ├── controller/
│   │   │   │       │   └── BankController.java
│   │   │   │       ├── exception/
│   │   │   │       │   ├── BankException.java
│   │   │   │       │   ├── ErrorDetails.java
│   │   │   │       │   └── GlobalExceptionHandler.java
│   │   │   │       ├── model/
│   │   │   │       │   ├── Account.java
│   │   │   │       │   ├── Customer.java
│   │   │   │       │   └── Transaction.java
│   │   │   │       ├── repository/
│   │   │   │       │   └── CustomerRepository.java
│   │   │   │       ├── service/
│   │   │   │       │   ├── BankService.java
│   │   │   │       │   ├── NotificationService.java
│   │   │   │       └── BankXApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── static/
│   │   │   │   ├── accounts.html
│   │   │   │   ├── customers.html
│   │   │   │   ├── index.html
│   │   │   │   ├── payments.html
│   │   │   │   └── styles.css
│   └── test/
│       └── java/
│           └── com/
│               └── bankx/
│                   └── bankx/
│                       └── BankxApplicationTests.java
└── pom.xml
```

## Running the Application

1. **Build the project:**
   ```bash
   mvn clean package
   ```

2. **Run the application:**
   ```bash
   java -jar target/BankApplication-1.0.jar
   ```

3. **Access the frontend:**
   Open a web browser and navigate to `http://localhost:8080/`.

## Using Docker

1. **Build the Docker image:**
   ```bash
   docker build -t bankx .
   ```

2. **Run the Docker container:**
   ```bash
   docker run -p 8080:8080 bankx
   ```

## Features

* **Customer Onboarding:**
    * Allows users to create new customer accounts with basic details.

* **Account Management:**
    * Provides functionality to transfer funds between current and savings accounts.
    * Allows users to view their transaction history and account balances.

* **Payments:**
    * Enables users to make payments to other accounts with a transaction fee.

## Notes

* The application uses an in-memory H2 database for development and testing purposes.
* The frontend is a basic HTML/CSS/JavaScript implementation.
* The application includes error handling for common banking scenarios like insufficient funds and customer not found.
* The notification system is currently implemented as simple console output.
* The application is deployed using Docker for easy containerization and deployment.

## Future Enhancements

* Implement a more robust notification system using email or SMS.
* Add support for different account types (e.g., checking accounts).
* Integrate a third-party payment gateway for real-time payments.
* Improve the frontend user interface with a more user-friendly design.
* Add security features like user authentication and authorization.
* Migrate to a production-ready database (e.g., MySQL or PostgreSQL).

This project serves as a starting point for building a more comprehensive banking application. The provided code can be modified and extended to meet specific requirements.
