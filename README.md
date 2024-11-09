# Blog Application API

This project is a backend REST API for a blog application developed using Java Spring Boot. The API allows for CRUD operations on users, posts, roles, and comments. Authentication and authorization are implemented using Spring Security.

## Technologies Used

- Java Spring Boot - For the backend framework
- Spring Security - For handling authentication and authorization
- Spring Data JPA - For database operations
- MySQL - As the database

## Project Structure

The project follows a modular structure with packages dedicated to various layers and responsibilities.

## Key Packages

- config: Contains configuration files for Spring Security and other settings.
- controllers: Contains REST controllers that handle HTTP requests and responses.
- dto: Data Transfer Objects for carrying data between layers.
- entities: Defines the data models for User, Role, Post, and Content.
- exceptions: Custom exception handling for the application.
- payloads: Contains response and request payloads such as ApiResponse and PostResponse.
- repositories: Repository interfaces for database interactions using Spring Data JPA.
- security: Manages authentication and authorization configurations.
- service: Service layer for business logic.
- util: Utility classes for common functions.

## Entity Classes

- User: Represents the user entity with fields such as id, name, email, etc.
- Role: Represents different roles (e.g., admin, user).
- Post: Represents a blog post with fields such as id, title, content, etc.
- Content: Represents additional content details for posts.

  ## Features

- User Management: Create, update, and delete users.
- Post Management: Create, update, delete, and fetch posts.
- Category Management: Add, delete, and fetch categories.
- Comment Management: Add comments to posts.
- Authentication and Authorization: Secured endpoints using JWT and role-based access control.

## Endpoints

| HTTP Method | Endpoint                   | Description                 |
|-------------|----------------------------|-----------------------------|
| `POST`      | `/addUser`                 | Create a new user           |
| `GET`       | `/getAllUser`              | Get all users               |
| `GET`       | `/getUserById/{id}`        | Get a user by ID            |
| `DELETE`    | `/deleteUserById/{id}`     | Delete a user by ID         |
| `PUT`       | `/updateUser`              | Update a user's details     |
| `POST`      | `/addCategory`             | Add a new category          |
| `DELETE`    | `/deleteCategoryById/{id}` | Delete a category by ID     |
| `GET`       | `/getAllCategory`          | Get all categories          |
| `POST`      | `/createPost`              | Create a new post           |
| `GET`       | `/getPostByCategory/{id}`  | Get posts by category       |
| `GET`       | `/getPostByUser/{id}`      | Get posts by user           |
| `GET`       | `/getPostById/{id}`        | Get a post by ID            |
| `GET`       | `/getAllPost`              | Get all posts               |
| `DELETE`    | `/deletePost/{id}`         | Delete a post by ID         |
| `GET`       | `/searchPost?query={text}` | Search posts by keyword     |
| `POST`      | `/uploadPostImage`         | Upload an image for a post  |
| `PUT`       | `/updatePost`              | Update a post's details     |
| `GET`       | `/getPostByImageName/{name}` | Get post by image name  |
| `POST`      | `/addComment`              | Add a comment to a post     |

## Getting Started

# Prerequisites

- Java 17 or later
- Maven for dependency management
- MySQL server
  
