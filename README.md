# Scooter Ride API

## Overview
The **Scooter Ride API** is a RESTful web service designed to manage scooter vehicles and riders for a ride-sharing application. It provides endpoints for vehicle operations, rider management, and authentication while integrating key functionalities like reservations and ride tracking. Built with **Spring Boot**, it leverages robust JPA for data persistence and follows RESTful best practices.

## Features
### Vehicle Management
- **Register a New Vehicle**
- **Retrieve Vehicle Information**
- **Update Vehicle Details**
- **Delete a Vehicle**
- **Reserve and Unreserve a Vehicle**

### Rider Management
- **Register Riders**
- **Authenticate Riders**
- **Retrieve Rider Details**

### Security
- Token-based authentication using **Bearer Tokens** for secure API access.

### Reservation Management
- Manage active and completed reservations for scooters.

---

## Installation

### Prerequisites
1. Java 11 or later
2. Gradle 7+
3. MySQL or any other supported relational database

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/scooterRide-Back.git
   cd scooterRide-Back
   ```
2. (Optional)H2 inmemory DB  used out of the box.

    Steps for external DB config:

   Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/scooter_db
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Build and run the application:
   ```bash
   ./gradlew clean build
   ./gradlew bootRun
   ```
5. Access the API at `http://localhost:8080/api`.
   Swagger documentation available at `http://localhost:8080/swagger-ui/index.html`

---

## API Endpoints

### Vehicle Endpoints
1. **Register New Vehicle**
   - `POST /api/vehicle/registerNew`
   - Request Body:
     ```json
     {
       "stateOfCharge": 85,
       "location": { "latitude": 37.7749, "longitude": -122.4194 },
       "reserved": false
     }
     ```
   - Response:
     ```json
     "created vehicle ID: {id}"
     ```

2. **Get Vehicle Data**
   - `GET /api/vehicle/getData`
   - Request Body:
     ```json
     1  // Vehicle ID
     ```
   - Response:
     ```json
     {
       "id": 1,
       "stateOfCharge": 85,
       "location": { "latitude": 37.7749, "longitude": -122.4194 },
       "reserved": false
     }
     ```

3. **Update Vehicle**
   - `PUT /api/vehicle/update`
   - Request Body:
     ```json
     {
       "id": 1,
       "stateOfCharge": 75,
       "reserved": true
     }
     ```
   - Response:
     ```json
     "updated vehicle: 1"
     ```

4. **Delete Vehicle**
   - `DELETE /api/vehicle/delete`
   - Request Body:
     ```json
     1  // Vehicle ID
     ```
   - Response:
     ```json
     "deleted vehicle with ID: 1"
     ```

5. **Reserve Vehicle**
   - `POST /api/vehicle/startRide`
   - Request Body:
     ```json
     1  // Vehicle ID
     ```
   - Response:
     ```json
     "1"  // Vehicle ID
     ```

6. **Unreserve Vehicle**
   - `POST /api/vehicle/endRide`
   - Request Body:
     ```json
     1  // Vehicle ID
     ```
   - Response:
     ```json
     "1"  // Vehicle ID
     ```

### Rider Endpoints
1. **Register Rider**
   - `POST /api/users/register`
   - Request Body:
     ```json
     {
       "username": "john_doe",
       "password": "securePassword123"
     }
     ```
   - Response:
     ```json
     "User registered successfully"
     ```

2. **Login**
   - `POST /api/users/login`
   - Request Body:
     ```json
     {
       "username": "john_doe",
       "password": "securePassword123"
     }
     ```
   - Response:
     ```json
     "Login successful: {token}"
     ```

3. **Get User Data**
   - `GET /api/users/getUserData`
   - Request Header:
     ```json
     "Authorization: Bearer {token}"
     ```
   - Response:
     ```json
     {
       "id": 1,
       "username": "john_doe",
       "email": "john_doe@example.com"
     }
     ```

---

## Testing
### Running Unit Tests
Run the following command to execute the test suite:
```bash
./gradlew test
```

### Key Tests
- VehicleControllerTest
  - Tests all vehicle-related API endpoints.
- RiderControllerTest
  - Tests rider registration, authentication, and data retrieval.

---

## Security
The API uses **Bearer Token Authentication** for secure access. Tokens are validated for every request requiring user authentication.

### Example Token Format:
```
Authorization: Bearer {your_token_here}
```

---

## Contribution Guidelines
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit and push your changes.
4. Submit a pull request for review.

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact
For any questions or feedback, feel free to reach out:
- **Email:** tiitkroon@gmail.com
- **GitHub:** [ScooterRideAPI](https://github.com/KroonTiit/scooterRide-Back)

