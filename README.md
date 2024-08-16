# BankX

BankX is a Spring Boot application that simulates basic banking operations. It provides a RESTful API for customer management, account transactions, and balance inquiries.

## Features

- Customer onboarding
- Account management (Current and Savings accounts)
- Fund transfers between accounts
- Payment processing
- Transaction history
- Balance inquiries
- Automated notifications for transactions

## Technologies Used

- Java 17
- Spring Boot 3.2.8
- Spring Data JPA
- H2 Database (in-memory)
- Maven

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.6.3 or later

### Running the Application

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/bankx.git
   cd bankx
   ```

2. Build the project:
   ```
   ./mvnw clean install
   ```

3. Run the application:
   ```
   ./mvnw spring-boot:run
   ```

The application will start running at `http://localhost:8080`.

## API Endpoints

- `POST /api/bank/customers`: Onboard a new customer
- `GET /api/bank/customers/{id}`: Get customer details
- `GET /api/bank/customers`: Get all customers
- `POST /api/bank/customers/{id}/transferToSavings`: Transfer funds to savings account
- `POST /api/bank/customers/{id}/transferToCurrent`: Transfer funds to current account
- `POST /api/bank/payment`: Make a payment
- `GET /api/bank/customers/{id}/transactions`: Get customer's transaction history
- `GET /api/bank/customers/{id}/balance`: Get customer's account balances

## Database

The application uses an H2 in-memory database. You can access the H2 console at `http://localhost:8080/h2-console` with the following details:

- JDBC URL: `jdbc:h2:mem:bankdb`
- Username: `sa`
- Password: (leave blank)

## Testing

Run the tests using:

```
./mvnw test
```

