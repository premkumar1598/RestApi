# 📝 Spring Boot Blog Post REST API

This project is a simple Spring Boot RESTful API for managing blog posts and their associated comments.
It follows best practices using DTOs, JPA, exception handling, and a clean layered architecture.

---

## 🚀 Features

- CRUD operations for **Blog Posts**
- Add and retrieve **Comments**
- Clean **DTO** conversion using `ModelMapper`
- Exception handling with custom error responses
- Uses **Spring Data JPA** and **MySQL**
- Modular code structure

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL (or H2 for testing)
- Lombok
- Maven
- ModelMapper

---

## 📁 Project Structure

src/
├── controller/ # REST controllers
├── service/ # Service layer
├── entity/ # JPA entities
├── repository/ # Repositories
├── payload/ # DTOs and response models
├── exception/ # Custom exceptions and handlers
└── utils/ # Constants and helpers

🔌 API Endpoints
📄 Posts
Method	Endpoint	Description
GET	/api/posts	Get all posts
POST	/api/posts	Create new post
GET	/api/posts/{id}	Get post by ID
PUT	/api/posts/{id}	Update post
DELETE	/api/posts/{id}	Delete post

💬 Comments
Method	Endpoint	Description
POST	/api/posts/{id}/comments	Add comment to post
GET	/api/posts/{id}/comments	Get all comments for a post

🧪 Example Request – Create Post
 
{
  "title": "Spring Boot Basics",
  "description": "Intro to Spring Boot",
  "content": "Spring Boot simplifies Spring application development."
}
🧪 Example Response – Get Post with Comments
 
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

