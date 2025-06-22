
# Notifications

A Spring Boot-based Notification Management System for user and admin management, notification delivery, and preference handling.  

Developed as an internship project at CrocoBet.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Data Models](#data-models)
- [Security](#security)
- [UI Overview](#ui-overview)
- [Development & Testing](#development--testing)
- [References](#references)

---

## Features

- User registration and authentication (Admin/Client roles)
- Admin dashboard for user management (CRUD)
- Client dashboard for managing notification preferences
- Notification sending (admin to all/individual users)  << under development >>
- Notification delivery status tracking                 << under development >>
- RESTful API with role-based access control
- In-memory H2 database for development/testing
- Thymeleaf-based web UI                                 << under development >>

---

## Architecture

- **Spring Boot**: Main application framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: ORM and data access
- **Thymeleaf**: Server-side HTML rendering               << under development >>
- **H2 Database**: In-memory database for development
- **Maven**: Build and dependency management

---

## Project Structure

```
Notifications/
  ├── src/
  │   ├── main/
  │   │   ├── java/com/NitsaBedianashvili/Notifications/
  │   │   │   ├── config/           # Security and app config
  │   │   │   ├── controller/       # API and web controllers
  │   │   │   ├── exception/        # Custom exceptions
  │   │   │   ├── model/            # JPA entities
  │   │   │   ├── repository/       # Spring Data repositories
  │   │   │   ├── security/         # Custom security logic
  │   │   │   ├── service/          # Business logic
  │   │   │   └── NotificationsApplication.java # Main entry point
  │   │   ├── resources/
  │   │   │   ├── application.properties
  │   │   │   ├── static/           # CSS/JS assets
  │   │   │   └── templates/        # Thymeleaf HTML templates
  │   └── test/
  ├── pom.xml
  ├── mvnw, mvnw.cmd
  └── HELP.md
```

---

## Getting Started

### Prerequisites

- Java 17+ (project uses Java 24 in pom.xml)
- Maven 3.6+
- Git

### Build & Run

```bash
# Clone the repository
git clone <repo-url>
cd Notifications

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The app will start on [http://localhost:8080](http://localhost:8080).

---

## Configuration

Main settings are in `src/main/resources/application.properties`:

- **Database**: Uses H2 in-memory (`jdbc:h2:mem:users`)
- **H2 Console**: Enabled at `/h2-console`
- **JPA**: `create-drop` mode for schema
- **Default Admin**: Username/password set in properties
- **Security**: Debug logging enabled

Example:
```properties
spring.datasource.url=jdbc:h2:mem:users
spring.datasource.username=nits
spring.datasource.password=a
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create-drop

spring.security.user.name=nits
spring.security.user.password=a
```

---

## API Endpoints

### Public

- `GET /`  
  Welcome message.

- `POST /registerUser`  
  Register a new user(admin or client), no authorisation needed.

### Admin 
  Requires authorisation

- `GET /admin/{id}/clientInfo`  
  List all client notification preferences.

- `POST /admin/{id}/addUser`  
  Add a new user.

- `PUT /admin/{id}/updateUser`  
  Update user info.

- `DELETE /admin/{id}/deleteUser`  
  Delete a user.




- `POST /notificationHub/admin/{ID}/send`  
  Send notification to a user.

- `POST /notificationHub/admin/{ID}/sendAll`  
  Send notification to all users.

- `GET /notificationHub/admin/{ID}/allNotifications`  
  List all notifications.

- `GET /notificationHub/admin/{ID}/allNotifications/sentByMe`  
  List notifications sent by this admin.

### Client
  Requires authorisation

- `PUT /client/{id}`  
  Update notification preferences.

- `DELETE /client/{id}`  
  Delete own account.




- `GET /notificationHub/client/{ID}`  
  Get user's inbox.

- `POST /notificationHub/client/{ID}/markAsRead`  
  Mark a message as read.

- `POST /notificationHub/client/{ID}/markAllAsRead`  
  Mark all messages as read.


---

## Data Models

### User

- `Long ID`
- `String name`
- `String surname`
- `String email`
- `String address`
- `Integer phoneNum`
- `String username`
- `String password`
- `String role` (ADMIN/CLIENT)
- `NotificationPreference notificationPreference`


### NotificationPreference

- `Long ID`
- `Boolean emailNotif`
- `Boolean postalNotif`
- `Boolean telNotif`
- `User user`


### Notification

- `Long messageID`
- `String message`
- `Long recipientID`
- `Long senderID`
- `DELIVERY_STATUS deliveryStatus` (SENT, DELIVERED, FAILED)
---

## Security

- **Spring Security** with role-based access (`ADMIN`, `CLIENT`)
- Custom `UserPrincipal` and `UserSecurity` for fine-grained access control
- Passwords stored with BCrypt
- CSRF disabled for API endpoints (see `SecurityConfig`)
- H2 console protected by same-origin policy

---

## UI Overview    

<< under development >>

- **Thymeleaf** templates for login, registration, admin dashboard, and client dashboard 
- Bootstrap for responsive design
- Admins can manage users and send notifications
- Clients can manage their notification preferences

---

## Development & Testing

- **H2 Database**: In-memory, resets on restart 
- **Spring Boot DevTools**: Hot reload enabled
- **Unit/Integration Tests**: Place tests in `src/test/java`     << under development >>
- **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Thymeleaf](https://www.thymeleaf.org/)
- [H2 Database](https://www.h2database.com/)
- See `HELP.md` for more guides and links

---

## License

This project is for educational/internship purposes at CrocoBet.
