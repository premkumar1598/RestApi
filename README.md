# 📝 Spring Boot Blog Post REST API

This project is a simple **Spring Boot RESTful API** for managing blog posts and their associated comments.  
It follows best practices with **DTOs, JPA, exception handling, security (JWT), and a clean layered architecture**.

---

## 🚀 Features

- CRUD operations for **Blog Posts**
- Add and retrieve **Comments**
- Clean **DTO** conversion using `ModelMapper`
- Exception handling with custom error responses
- Uses **Spring Data JPA** and **MySQL**
- Modular code structure
- **Role-Based Access Control (RBAC):**
  - `ADMIN`: Full access (create, update, delete posts)
  - `USER`: Can only read posts and add comments
- **Spring Security with JWT Authentication**
  - Secure login & token-based authentication
  - User credentials and roles stored in the database
  - Admin-only operations protected via JWT roles

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL (or H2 for testing)
- Lombok
- Maven
- ModelMapper
- Spring Security (JWT authentication)

---

## 📁 Project Structure

```
src/
├── controller/   # REST controllers
├── service/      # Service layer
├── entity/       # JPA entities
├── repository/   # Repositories
├── payload/      # DTOs and response models
├── exception/    # Custom exceptions and handlers
└── security/     # JWT config, filters, and authentication
```

---

## 🔐 Authentication & Security

### 1. Register a User
**Endpoint:** `POST /api/auth/register`  
```json
{
  "name": "Prem",
  "username": "prem123",
  "email": "prem@example.com",
  "password": "mypassword"
}
```

### 2. Login
**Endpoint:** `POST /api/auth/login`  
```json
{
  "usernameOrEmail": "prem123",
  "password": "mypassword"
}
```
✅ Returns a **JWT token** that must be included in the `Authorization` header for secured endpoints:

```
Authorization: Bearer <JWT_TOKEN>
```

### 3. Role Restrictions
- **USER**: Can read posts & add comments  
- **ADMIN**: Can create, update, and delete posts

---

## 🔌 API Endpoints

### 📄 Posts
| Method | Endpoint         | Description         | Role Required |
|--------|------------------|---------------------|---------------|
| GET    | /api/posts       | Get all posts       | USER / ADMIN |
| POST   | /api/posts       | Create new post     | ADMIN        |
| GET    | /api/posts/{id}  | Get post by ID      | USER / ADMIN |
| PUT    | /api/posts/{id}  | Update post         | ADMIN        |
| DELETE | /api/posts/{id}  | Delete post         | ADMIN        |

### 💬 Comments
| Method | Endpoint                    | Description               | Role Required |
|--------|-----------------------------|---------------------------|---------------|
| POST   | /api/posts/{id}/comments    | Add comment to a post     | USER / ADMIN |
| GET    | /api/posts/{id}/comments    | Get all comments on a post| USER / ADMIN |

---

## 🧪 Example Request – Create Post (Admin Only)

```http
POST /api/posts
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```
```json
{
  "title": "Spring Boot Basics",
  "description": "Intro to Spring Boot",
  "content": "Spring Boot simplifies Spring application development."
}
```

---

## 🧪 Example Response – Get Post with Comments

```json
{
  "id": 2,
  "title": "Spring Boot Basics",
  "description": "Intro to Spring Boot",
  "content": "Spring Boot simplifies Spring application development.",
  "comments": [
    {
      "id": 1,
      "name": "John",
      "email": "john@example.com",
      "body": "Very helpful article!"
    }
  ]
}
```
