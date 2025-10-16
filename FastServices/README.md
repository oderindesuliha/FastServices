# FastService

FastService is a smart queue and appointment management system built with Spring Boot and PostgreSQL. The system aims to reduce overcrowding and improve service efficiency in high-traffic environments such as banks, hospitals, and government offices.

## Problem

Traditional queuing and appointment systems often lead to:
- Long waiting times and overcrowding
- Inefficient resource allocation
- Lack of transparency in service delivery
- Exclusion of individuals without smartphones
- Poor customer experience and satisfaction

These issues are particularly prominent in sectors like healthcare, banking, and public services where large numbers of people need to access services daily.

## Solution (FastService)

FastService provides a comprehensive solution that digitizes and optimizes queue and appointment management. The system enables organizations to create service points (e.g., "Customer Service", "Cashier") and generate QR codes for each service. Customers can join queues by scanning these QR codes, while those without smartphones can check in through kiosks or with staff assistance. The platform ensures inclusive, transparent, and efficient service delivery for all users.

## Core Features

- **Organization Management**: Organizations can register and manage their service offerings
- **Service Points**: Create and manage different service points like "Customer Service" or "Cashier"
- **QR Code Generation**: Automatically generate QR codes for each service point
- **Queue Management**: Digital queuing system with real-time updates
- **Appointment Booking**: Schedule appointments in advance
- **Multi-Channel Access**: Smartphone scanning, kiosks, and staff-assisted check-in
- **User Management**: Admin panel for managing users and monitoring system performance
- **Real-time Notifications**: Email confirmations and status updates

## Tech Stack

- **Backend**: Spring Boot 3.5.6
- **Database**: PostgreSQL
- **Security**: Spring Security with JWT authentication
- **QR Code Generation**: ZXing library
- **Java Version**: Java 21
- **Build Tool**: Maven
- **Additional Libraries**: Lombok, ModelMapper, Hibernate Validator

## Setup and Configuration

### Environment Variables

The application uses environment variables for configuration. Create a `.env` file in the project root based on the `.env.example` template:

```
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/fastservice
SPRING_DATASOURCE_USERNAME=your_db_username
SPRING_DATASOURCE_PASSWORD=your_db_password

# JWT Configuration
APP_JWT_SECRET=your_jwt_secret_key_here
APP_JWT_EXPIRATION_MILLISECONDS=604800000

# Email Configuration
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587
SMTP_USERNAME=your_email@gmail.com
SMTP_PASSWORD=your_app_password

# Server Configuration
SERVER_PORT=8080
CORS_ALLOWED_ORIGINS=*
```

### Running the Application

1. Set up your environment variables in a `.env` file
2. Run the application with: `./mvnw spring-boot:run`

## API Documentation

Comprehensive API documentation is available in the [API_DOCUMENTATION.md](API_DOCUMENTATION.md) file. This includes all endpoints, request/response formats, and authentication details needed to integrate with the backend.

## Impact

FastService transforms how organizations deliver services by:
- Reducing physical waiting times by up to 70%
- Improving customer satisfaction through transparent queuing
- Enabling better resource allocation and staff planning
- Providing inclusive access for all customers, regardless of technical capability
- Offering real-time insights into service performance
- Decreasing overcrowding in physical locations
- Streamlining operations for banks, hospitals, and government offices

The system ensures that service delivery is fair, efficient, and accessible to everyone, creating a better experience for both customers and service providers.