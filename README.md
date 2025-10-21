# Finance Management Application 

A personal finance tracking and planning application that helps users 
monitor income, control spending and make informed financial decisions
through data visualization and intelligent insights. 

## Core features 
- User Management and security: user authentication and authorization
- Income Tracking: tracking income sources e.g. salary, business, loan etc. 
- Expense Tracking: categorize expenses as either necessary or luxury. 
- Budgeting: create customized budgets, spending alerts and notifications
- Financial Analysis: dashboards, charts ang graphs used to detect trends and patterns
- Financial Planning: patterns are used to identify areas that can be improved.
- Goal Setting: set short, medium and long term financial goals. 

## Tech Stack 
* Spring Boot Ecosystem: core framework 
* Spring Boot 3.5.6
* Java 21
* Sping JPA with Hibernate - for persistence 
* Spring Security with JWT - for security 
* Spring Validation - for data validation and enforcing constraints 
* PostgreSQL 17.5 - database 
* Flyway - database migration management 
* Maven - for managing dependencies 
* JUnit 5 and Mockito - for testing 
* Lombok - for reducing boilerplate code 
* OpenAPI(Swagger) - for documentation 
* Git and Github - for versions control 
* Docker - for deployment 

## Security Features 
* JWT-based authentication with refresh tokens
* Password encryption using BCrypt
* Role-based authorization (USER, ADMIN)
* CORS configuration for web client integration
* Input validation and SQL injection prevention
* Secure logging configuration (CVE-2025-11226 mitigation applied)

## Prerequisites
* Java 21 or later
* Maven 3.6 or later
* PostgreSQL 17.5 or later
* Git

## Environment Setup

**Copy the environment template:**
   bash
   cp .env.example .env

## Installation and Setup

1. Clone the repository 
git clone https://github.com/kevinnyangweso/finance-demo.git
cd finance-demo

2. Setting up the Database
- Create database : CREATE DATABASE finance_demo;

- Create user (optional)
CREATE USER finance_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE finance_demo TO finance_user;

3. Configuration - src/main/resources/application.properties 

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/finance_demo_db
spring.datasource.username=finance_demo_user
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server Configuration
server.port=8080

# Logging Configuration (Security Hardened)
logging.level.com.kevin.financedemo=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n

# JWT Configuration
jwt.secret=your-jwt-secret-key-minimum-32-characters-long
jwt.expiration=86400000

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration 

Build the application

bash 
- mvn clean install 

- Run the Application 

bash
- mvn spring-boot:run

## API Documentation
Once the application is running, access the API documentation at:
Swagger UI: http://localhost:8080/swagger-ui.html
OpenAPI JSON: http://localhost:8080/v3/api-docs

## Project Structure
text
src/main/java/com/kevin/financedemo/
├── config/          # Configuration classes
├── controller/      # REST API controllers
├── service/         # Business logic layer
├── repository/      # Data access layer
├── entity/          # JPA entities
├── dto/            # Data transfer objects
├── security/        # Security configuration and JWT
├── exception/       # Custom exception handling
└── FinanceDemoApplication.java


## Key Endpoints
1. Authentication
- POST /api/auth/signup - User registration
- POST /api/auth/login - User login
- POST /api/auth/refresh - Refresh JWT token

2. Income Management
- GET /api/incomes - Get all incomes for user
- POST /api/incomes - Add new income
- PUT /api/incomes/{id} - Update income
- DELETE /api/incomes/{id} - Delete income

3. Expense Management
- GET /api/expenses - Get all expenses for user
- POST /api/expenses - Add new expense
- GET /api/expenses/categories - Get expense categories
- GET /api/expenses/analysis - Get spending analysis

4. Budget Management
- GET /api/budgets - Get user budgets
- POST /api/budgets - Create new budget
- GET /api/budgets/{id}/progress - Get budget progress

5. Reports & Analytics
- GET /api/reports/monthly - Monthly financial report
- GET /api/reports/spending-trends - Spending trend analysis
- GET /api/reports/financial-health - Financial health score

## Testing
Run the test suite:

bash
# Run all tests
- mvn test

## Deployment
Using Docker
bash
# Build Docker image
docker build -t finance-demo .
# Run container
docker run -p 8080:8080 finance-demo


## Docker Compose (Includes PostgreSQL)
bash
docker-compose up -d


## Contributing
1. Fork the repository
2. Create a feature branch (git checkout -b feature/amazing-feature)
3. Commit your changes (git commit -m 'Add amazing feature')
4. Push to the branch (git push origin feature/amazing-feature)
5. Open a Pull Request

## License
This project is currently unlicensed. Please contact the maintainers for 
licensing information.
Support

## For support and questions:
1. Create an issue in the GitHub repository
2. Email: nyangwesokevin1@gmail.com

## Roadmap
1. Mobile app development
2. AI-powered financial insights
3. Investment portfolio integration
4. Multi-currency support
5. Bank synchronization API
6. Tax planning features

Note: This application is for educational and personal use. 
Always consult with financial professionals for important financial decisions.