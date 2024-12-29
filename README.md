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

