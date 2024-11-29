# Golf Club API

## Overview
The Golf Club API is a RESTful service for managing members and tournaments. It supports:
- Adding, updating, deleting, and fetching members.
- Adding members to tournaments.
- Searching for tournaments and members with flexible filters.

---

## Prerequisites
Ensure the following are installed:
1. **Docker** and **Docker Compose**.
2. **Java 17+**.
3. **Maven** (if you need to build locally without Docker).

---

## Running the Application

### 1. Clone the Repository
```bash
git clone <your-repository-url>
cd Golf-Club-API
```

### 2. Build and Run with Docker
Ensure Docker is installed and running, then:
```bash
docker-compose up --build
```
This will:
- Build the application.
- Start the MySQL and API containers.

### 3. Access the Application
- API is available at: `http://localhost:8080`
- MySQL database is accessible on: `localhost:3307`

### 4. Stopping the Application
To stop the running containers:
```bash
docker-compose down
```

---

## Endpoints

### **Members**
- **Add a member**:  
  `POST /members`  
  Example Payload:
  ```json
  {
    "name": "John Doe",
    "address": "123 Elm Street",
    "email": "johndoe@example.com",
    "phoneNumber": "123-456-7890",
    "membershipStartDate": "2023-01-15",
    "membershipDuration": 12
  }
  ```
- **Fetch all members**:  
  `GET /members`
- **Fetch a member by ID**:  
  `GET /members/{id}`
- **Search members by name**:  
  `GET /members/search?name={name}`  

### **Tournaments**
- **Create a tournament**:  
  `POST /tournaments`  
  Example Payload:
  ```json
  {
    "startDate": "2024-02-15",
    "endDate": "2024-02-20",
    "location": "Pebble Beach Golf Links",
    "entryFee": 150.0,
    "cashPrize": 5000.0
  }
  ```
- **Search tournaments**:  
  `GET /tournaments/search?location={location}&startDate={startDate}`  
  Example Query:
  ```
  /tournaments/search?location=Pebble Beach&startDate=2024-02-15
  ```
- **Add a member to a tournament**:  
  `POST /tournaments/{tournamentId}/addMember/{memberId}`
- **Fetch members in a tournament**:  
  `GET /tournaments/{tournamentId}/members`

---

## Database Configuration
The application uses **MySQL** with the following settings:
- **Database Name**: `golf`
- **Username**: `root`
- **Password**: `admin`

You can view or modify these settings in `docker-compose.yml` or `application.properties`.

---

## Example Workflow
1. Start the application using Docker:
   ```bash
   docker-compose up --build
   ```
2. Open Postman or use `curl` to test the APIs. Example:
   - Add a member:
     ```bash
     curl -X POST http://localhost:8080/members \
     -H "Content-Type: application/json" \
     -d '{
       "name": "John Doe",
       "address": "123 Elm Street",
       "email": "johndoe@example.com",
       "phoneNumber": "123-456-7890",
       "membershipStartDate": "2023-01-15",
       "membershipDuration": 12
     }'
     ```
   - Search tournaments:
     ```bash
     curl -X GET "http://localhost:8080/tournaments/search?location=Pebble Beach&startDate=2024-02-15"
     ```
