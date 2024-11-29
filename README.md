# Golf Club API

## Overview
The Golf Club API is a RESTful service for managing members and tournaments. It includes functionality for:
- Adding, updating, deleting, and fetching members.
- Adding members to tournaments.
- Searching tournaments and members.

## Prerequisites
Ensure you have the following installed:
1. Docker and Docker Compose.
2. Java 17+.
3. Maven (if you need to build locally without Docker).

## Running the Application

### 1. Clone the Repository
```bash
git clone <your-repository-url>
cd Golf-Club-API
2. Build and Run with Docker
Ensure Docker is installed and running, then:

bash
Copy code
docker-compose up --build
This will:

Build the application.
Start the MySQL and API containers.
3. Access the Application
API is available at http://localhost:8080.
MySQL database is accessible on localhost:3307.
4. Using Postman or cURL
You can test the following endpoints:

Members:

Add a member:
http
Copy code
POST /members
{
  "name": "John Doe",
  "address": "123 Elm Street",
  "email": "johndoe@example.com",
  "phoneNumber": "123-456-7890",
  "membershipStartDate": "2023-01-15",
  "membershipDuration": 12
}
Fetch all members:
http
Copy code
GET /members
Tournaments:

Create a tournament:
http
Copy code
POST /tournaments
{
  "startDate": "2024-02-15",
  "endDate": "2024-02-20",
  "location": "Pebble Beach",
  "entryFee": 150.0,
  "cashPrize": 5000.0
}
Add a member to a tournament:
http
Copy code
POST /tournaments/{tournamentId}/addMember/{memberId}
5. Stopping the Application
To stop the containers:

bash
Copy code
docker-compose down
Database Configuration
The application uses MySQL, configured as follows:

Database Name: golf
Username: root
Password: admin
You can view or modify these settings in docker-compose.yml or application.properties.
