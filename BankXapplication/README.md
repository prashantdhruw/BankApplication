# BankX Application

BankX is a web-based banking application built using Spring Boot and Java. It provides features for customer management, account management, and payment processing.

## Table of Contents
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Front-end](#front-end)
- [Exception Handling](#exception-handling)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Requirements
- Java 17
- Apache Maven 3.8.1 or later
- Spring Boot 3.2.8
- H2 In-memory Database

## Getting Started
1. Clone the repository:
   ```
   git clone https://github.com/yourusername/BankXapplication.git
   ```

2. Navigate to the project directory:
   ```
   cd BankXapplication
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. Access the application in your web browser at `http://localhost:8080`.

## Project Structure
The project follows a standard Maven project structure:

- `src/main/java`: Contains the main application source code
  - `com.bankx`: Base package for the application
    - `controller`: Contains the REST API controllers
    - `model`: Contains the domain model classes
    - `repository`: Contains the data access layer interfaces
    - `service`: Contains the business logic services
    - `exception`: Contains custom exception classes and global exception handler
- `src/main/resources`: Contains application configuration files and static resources
  - `application.properties`: Application configuration properties
  - `static`: Contains static HTML, CSS, and JavaScript files for the front-end
- `src/test`: Contains test classes for the application
- `pom.xml`: Maven configuration file for the project

## API Endpoints
The application exposes the following REST API endpoints:

- `POST /api/bank/customers`: Onboard a new customer
- `GET /api/bank/customers/{id}`: Get customer information by ID
- `GET /api/bank/customers`: Get all customers
- `POST /api/bank/customers/{id}/transferToSavings`: Transfer funds from current account to savings account
- `POST /api/bank/customers/{id}/transferToCurrent`: Transfer funds from savings account to current account
- `POST /api/bank/payment`: Make a payment between accounts
- `GET /api/bank/customers/{id}/transactions`: Get customer transactions
- `GET /api/bank/customers/{id}/balance`: Get customer balance information

## Front-end
The application includes a simple front-end built with HTML, CSS, and JavaScript. The front-end communicates with the backend API using AJAX requests with the Axios library.

The front-end consists of the following pages:

- `index.html`: Home page with navigation menu
- `customers.html`: Customer management page for onboarding customers and viewing customer information
- `accounts.html`: Account management page for transferring funds and viewing account information
- `payments.html`: Payment processing page for making payments between accounts

## Exception Handling
The application implements custom exception handling using the `BankException` class and a global exception handler `GlobalExceptionHandler`. The global exception handler handles both `BankException` and generic `Exception` and returns appropriate error responses.

## Testing
The project includes a basic test class `BankxApplicationTests` for testing the application context loading. You can add more test classes to cover different aspects of the application.

To run the tests, use the following command:
```
mvn test
```

## Contributing
Contributions to the BankX application are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

When contributing to this project, please follow the existing code style and conventions.

## License
This project is licensed under the [MIT License](LICENSE).