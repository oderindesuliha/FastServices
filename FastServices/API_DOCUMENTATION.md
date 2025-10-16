# FastService API Documentation

FastService is a smart queue and appointment management system built with Spring Boot and PostgreSQL. This document provides comprehensive documentation for all available API endpoints.

## Base URL

```
http://localhost:8080/api
```

## Authentication

Most endpoints require authentication using JWT tokens. Include the token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

## Error Responses

All error responses follow this format:

```json
{
  "message": "Error description"
}
```

## API Endpoints

### Authentication

#### Login
```
POST /auth/login
```

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password"
}
```

**Response:**
```json
{
  "accessToken": "jwt-token-string",
  "tokenType": "Bearer"
}
```

#### Register
```
POST /auth/register
```

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "password",
  "phone": "1234567890"
}
```

**Response:**
```json
{
  "id": "user-id",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "roles": ["CUSTOMER"]
}
```

### Organizations

#### Register Organization
```
POST /organizations/register
```

**Request Body:**
```json
{
  "name": "Union Bank",
  "description": "A leading banking institution",
  "contactEmail": "contact@unionbank.com",
  "contactPhone": "1234567890",
  "address": "123 Main St, City",
  "password": "password"
}
```

**Response:**
```json
{
  "id": "org-id",
  "name": "Union Bank",
  "description": "A leading banking institution",
  "contactEmail": "contact@unionbank.com",
  "contactPhone": "1234567890",
  "address": "123 Main St, City",
  "code": "ORG001"
}
```

#### Get Organization by ID
```
GET /organizations/{id}
```

**Response:**
```json
{
  "id": "org-id",
  "name": "Union Bank",
  "description": "A leading banking institution",
  "contactEmail": "contact@unionbank.com",
  "contactPhone": "1234567890",
  "address": "123 Main St, City",
  "code": "ORG001"
}
```

#### Get All Organizations
```
GET /organizations
```

**Response:**
```json
[
  {
    "id": "org-id",
    "name": "Union Bank",
    "description": "A leading banking institution",
    "contactEmail": "contact@unionbank.com",
    "contactPhone": "1234567890",
    "address": "123 Main St, City",
    "code": "ORG001"
  }
]
```

#### Update Organization
```
PUT /organizations/{id}
```

**Request Body:**
```json
{
  "name": "Union Bank",
  "description": "A leading banking institution",
  "contactEmail": "contact@unionbank.com",
  "contactPhone": "1234567890",
  "address": "123 Main St, City"
}
```

**Response:**
```json
{
  "id": "org-id",
  "name": "Union Bank",
  "description": "A leading banking institution",
  "contactEmail": "contact@unionbank.com",
  "contactPhone": "1234567890",
  "address": "123 Main St, City",
  "code": "ORG001"
}
```

#### Delete Organization
```
DELETE /organizations/{id}
```

**Response:**
```
204 No Content
```

#### Get Organization by Code
```
GET /organizations/code/{code}
```

**Response:**
```json
{
  "id": "org-id",
  "name": "Union Bank",
  "description": "A leading banking institution",
  "contactEmail": "contact@unionbank.com",
  "contactPhone": "1234567890",
  "address": "123 Main St, City",
  "code": "ORG001"
}
```

### Offerings (Services)

#### Create Offering
```
POST /offerings
```
*Requires ADMIN role*

**Request Body:**
```json
{
  "name": "Customer Service",
  "description": "General customer support",
  "organizationId": "org-id"
}
```

**Response:**
```json
{
  "id": "offering-id",
  "name": "Customer Service",
  "description": "General customer support",
  "organizationId": "org-id"
}
```

#### Get Offering by ID
```
GET /offerings/{id}
```

**Response:**
```json
{
  "id": "offering-id",
  "name": "Customer Service",
  "description": "General customer support",
  "organizationId": "org-id"
}
```

#### Get All Offerings
```
GET /offerings
```

**Response:**
```json
[
  {
    "id": "offering-id",
    "name": "Customer Service",
    "description": "General customer support",
    "organizationId": "org-id"
  }
]
```

#### Get Offerings by Organization ID
```
GET /offerings/organization/{organizationId}
```

**Response:**
```json
[
  {
    "id": "offering-id",
    "name": "Customer Service",
    "description": "General customer support",
    "organizationId": "org-id"
  }
]
```

#### Update Offering
```
PUT /offerings/{id}
```
*Requires ADMIN role*

**Request Body:**
```json
{
  "name": "Customer Service",
  "description": "General customer support",
  "organizationId": "org-id"
}
```

**Response:**
```json
{
  "id": "offering-id",
  "name": "Customer Service",
  "description": "General customer support",
  "organizationId": "org-id"
}
```

#### Delete Offering
```
DELETE /offerings/{id}
```
*Requires ADMIN role*

**Response:**
```
204 No Content
```

### Queues

#### Create Queue
```
POST /queues
```
*Requires ADMIN role*

**Request Body:**
```json
{
  "name": "Main Queue",
  "description": "Main service queue",
  "organizationId": "org-id",
  "offeringId": "offering-id"
}
```

**Response:**
```json
{
  "id": "queue-id",
  "name": "Main Queue",
  "description": "Main service queue",
  "organizationId": "org-id",
  "offeringId": "offering-id"
}
```

#### Get Queue by ID
```
GET /queues/{id}
```

**Response:**
```json
{
  "id": "queue-id",
  "name": "Main Queue",
  "description": "Main service queue",
  "organizationId": "org-id",
  "offeringId": "offering-id"
}
```

#### Get All Queues
```
GET /queues
```

**Response:**
```json
[
  {
    "id": "queue-id",
    "name": "Main Queue",
    "description": "Main service queue",
    "organizationId": "org-id",
    "offeringId": "offering-id"
  }
]
```

#### Get Queues by Organization ID
```
GET /queues/organization/{organizationId}
```

**Response:**
```json
[
  {
    "id": "queue-id",
    "name": "Main Queue",
    "description": "Main service queue",
    "organizationId": "org-id",
    "offeringId": "offering-id"
  }
]
```

#### Get Queues by Offering ID
```
GET /queues/offering/{offeringId}
```

**Response:**
```json
[
  {
    "id": "queue-id",
    "name": "Main Queue",
    "description": "Main service queue",
    "organizationId": "org-id",
    "offeringId": "offering-id"
  }
]
```

#### Update Queue
```
PUT /queues/{id}
```
*Requires ADMIN role*

**Request Body:**
```json
{
  "name": "Main Queue",
  "description": "Main service queue",
  "organizationId": "org-id",
  "offeringId": "offering-id"
}
```

**Response:**
```json
{
  "id": "queue-id",
  "name": "Main Queue",
  "description": "Main service queue",
  "organizationId": "org-id",
  "offeringId": "offering-id"
}
```

#### Delete Queue
```
DELETE /queues/{id}
```
*Requires ADMIN role*

**Response:**
```
204 No Content
```

### Appointments

#### Create Appointment
```
POST /appointments
```

**Request Body:**
```json
{
  "userId": "user-id",
  "offeringId": "offering-id",
  "appointmentDate": "2025-10-20T10:00:00",
  "status": "PENDING"
}
```

**Response:**
```json
{
  "id": "appointment-id",
  "userId": "user-id",
  "offeringId": "offering-id",
  "appointmentDate": "2025-10-20T10:00:00",
  "status": "PENDING"
}
```

#### Get Appointment by ID
```
GET /appointments/{id}
```

**Response:**
```json
{
  "id": "appointment-id",
  "userId": "user-id",
  "offeringId": "offering-id",
  "appointmentDate": "2025-10-20T10:00:00",
  "status": "PENDING"
}
```

#### Get All Appointments
```
GET /appointments
```

**Response:**
```json
[
  {
    "id": "appointment-id",
    "userId": "user-id",
    "offeringId": "offering-id",
    "appointmentDate": "2025-10-20T10:00:00",
    "status": "PENDING"
  }
]
```

#### Get Appointments by Customer ID
```
GET /appointments/customer/{customerId}
```

**Response:**
```json
[
  {
    "id": "appointment-id",
    "userId": "user-id",
    "offeringId": "offering-id",
    "appointmentDate": "2025-10-20T10:00:00",
    "status": "PENDING"
  }
]
```

#### Get Appointments by Offering ID
```
GET /appointments/offering/{offeringId}
```

**Response:**
```json
[
  {
    "id": "appointment-id",
    "userId": "user-id",
    "offeringId": "offering-id",
    "appointmentDate": "2025-10-20T10:00:00",
    "status": "PENDING"
  }
]
```

#### Get Appointments by Queue ID
```
GET /appointments/queue/{queueId}
```

**Response:**
```json
[
  {
    "id": "appointment-id",
    "userId": "user-id",
    "offeringId": "offering-id",
    "appointmentDate": "2025-10-20T10:00:00",
    "status": "PENDING"
  }
]
```

#### Update Appointment
```
PUT /appointments/{id}
```

**Request Body:**
```json
{
  "userId": "user-id",
  "offeringId": "offering-id",
  "appointmentDate": "2025-10-20T10:00:00",
  "status": "CONFIRMED"
}
```

**Response:**
```json
{
  "id": "appointment-id",
  "userId": "user-id",
  "offeringId": "offering-id",
  "appointmentDate": "2025-10-20T10:00:00",
  "status": "CONFIRMED"
}
```

#### Delete Appointment
```
DELETE /appointments/{id}
```

**Response:**
```
204 No Content
```

### Users

#### Create User
```
POST /users
```
*Requires ADMIN role*

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "password",
  "phone": "1234567890"
}
```

**Response:**
```json
{
  "id": "user-id",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "roles": ["CUSTOMER"]
}
```

#### Get User by ID
```
GET /users/{id}
```

**Response:**
```json
{
  "id": "user-id",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "roles": ["CUSTOMER"]
}
```

#### Get All Users
```
GET /users
```
*Requires ADMIN role*

**Response:**
```json
[
  {
    "id": "user-id",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890",
    "roles": ["CUSTOMER"]
  }
]
```

#### Update User
```
PUT /users/{id}
```

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "newpassword",
  "phone": "1234567890"
}
```

**Response:**
```json
{
  "id": "user-id",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "roles": ["CUSTOMER"]
}
```

#### Delete User
```
DELETE /users/{id}
```
*Requires ADMIN role*

**Response:**
```
204 No Content
```

#### Get User by Email
```
GET /users/email/{email}
```

**Response:**
```json
{
  "id": "user-id",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "roles": ["CUSTOMER"]
}
```

### QR Codes

#### Generate Organization QR Code
```
GET /qrcode/organization/{orgId}
```

**Response:**
```
Base64 encoded QR code image
```

#### Generate Offering QR Code
```
GET /qrcode/offering/{offeringId}
```

**Response:**
```
Base64 encoded QR code image
```

#### Generate Queue QR Code
```
GET /qrcode/queue/{queueId}
```

**Response:**
```
Base64 encoded QR code image
```

### Public Endpoints

These endpoints do not require authentication.

#### Get Organization by ID
```
GET /public/organizations/{id}
```

**Response:**
```json
{
  "id": "org-id",
  "name": "Union Bank",
  "description": "A leading banking institution",
  "contactEmail": "contact@unionbank.com",
  "contactPhone": "1234567890",
  "address": "123 Main St, City",
  "code": "ORG001"
}
```

#### Get Offerings by Organization ID
```
GET /public/organizations/{id}/offerings
```

**Response:**
```json
[
  {
    "id": "offering-id",
    "name": "Customer Service",
    "description": "General customer support",
    "organizationId": "org-id"
  }
]
```

#### Get Offering by ID
```
GET /public/offerings/{id}
```

**Response:**
```json
{
  "id": "offering-id",
  "name": "Customer Service",
  "description": "General customer support",
  "organizationId": "org-id"
}
```