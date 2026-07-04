<div align="center">

# 🛒 E-Commerce Backend API

**A secure, RESTful e-commerce backend built with Spring Boot 3, Spring Security (JWT), Spring Data JPA / Hibernate, and MySQL.**

<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
<img src="https://img.shields.io/badge/Hibernate-6.x-59666C?style=for-the-badge&logo=hibernate&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-8.x-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-3.9%2B-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
<img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge"/>

</div>

---

## 📌 Table of Contents

- [About the Project](#-about-the-project)
- [Key Features](#-key-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Domain Model](#-domain-model)
- [Authentication & Security](#-authentication--security)
- [API Reference](#-api-reference)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [Roadmap & Engineering Notes](#-roadmap--engineering-notes)
- [Author](#-author)
- [License](#-license)

---

## 🧩 About the Project

This is a backend **REST API for an online shopping platform**, built with **Spring Boot 3.5.3** on **Java 21**. It covers the core commerce domain end to end:

- **User accounts** with JWT-based registration and login
- **Product catalogue** with categories, search, and category filtering
- **Shopping cart** with line items and quantity management
- **Orders** created from a user, with cancellation
- **Wishlists** and **product reviews / ratings**

The codebase follows a clean, layered **Controller → Service → Repository → Entity** structure, uses **Spring Data JPA / Hibernate** for persistence against **MySQL**, and secures the API surface with **Spring Security** and **stateless JSON Web Tokens (JWT)**.

> Built as a hands-on backend project to demonstrate real-world Spring Boot skills: layered architecture, JPA relationship mapping, and token-based authentication.

---

## ✨ Key Features

- 🔐 **JWT Authentication** — stateless auth with `register` / `authenticate` endpoints issuing signed HS256 tokens
- 🔒 **BCrypt password hashing** — credentials are never stored in plain text
- 👤 **Spring Security integration** — `User` implements `UserDetails`; a custom `OncePerRequestFilter` validates the `Authorization: Bearer <token>` header on every request
- 🗂️ **8 REST controllers** — Auth, User, Product, Category, Cart, Order, Wishlist, Review
- 🧱 **10 JPA entities** with `@OneToOne`, `@OneToMany`, and `@ManyToOne` relationships
- 🔎 **Product search & filtering** — by name (case-insensitive contains) and by category
- 🛒 **Cart & order workflow** — add/update/remove cart items, place and cancel orders
- ⚡ **Spring Data JPA repositories** — zero-boilerplate CRUD plus derived query methods
- 🔄 **Auto schema management** — Hibernate `ddl-auto=update` generates and evolves tables
- 🧰 **Lombok** — `@Data`, `@Builder`, `@RequiredArgsConstructor` cut boilerplate
- 🔥 **DevTools** — hot restart during development
- 📦 **Maven Wrapper** — build without a local Maven install

---

## 🛠 Tech Stack

| Layer | Technology | Notes |
|---|---|---|
| Language | **Java 21** (LTS) | `pom.xml` → `<java.version>21</java.version>` |
| Framework | **Spring Boot 3.5.3** | `spring-boot-starter-parent` |
| Web | **Spring Web (Spring MVC)** | `spring-boot-starter-web` |
| Security | **Spring Security** + **JJWT 0.13.0** | `jjwt-api` / `jjwt-impl` / `jjwt-jackson` |
| Persistence | **Spring Data JPA / Hibernate 6.x** | `spring-boot-starter-data-jpa` |
| Validation | **Jakarta Bean Validation** | `spring-boot-starter-validation` |
| Database | **MySQL 8.x** | `mysql-connector-j` (runtime) |
| Connection Pool | **HikariCP** | Auto-configured by Spring Boot |
| Server | **Embedded Apache Tomcat 10.1** | Ships with the web starter |
| Boilerplate | **Lombok** | Compile-time annotation processing |
| Dev Experience | **Spring Boot DevTools** | Hot reload |
| Build | **Apache Maven** (+ wrapper) | `mvnw` / `mvnw.cmd` |

> Versions not pinned in `pom.xml` (Hibernate, Tomcat, HikariCP, Jackson) are managed transitively by the Spring Boot 3.5.3 dependency BOM.

---

## 🏗 Architecture

A classic layered Spring Boot architecture with a dedicated security layer:

```
                       HTTP (JSON) — Postman / Frontend
                                   │
                                   ▼
        ┌──────────────────────────────────────────────────┐
        │  SECURITY FILTER CHAIN                            │
        │  JwtAuthenticationFilter → validates Bearer token │
        │  BCrypt · stateless sessions · AuthenticationMgr  │
        └──────────────────────────┬───────────────────────┘
                                   ▼
        ┌──────────────────────────────────────────────────┐
        │  CONTROLLER LAYER  (@RestController)              │
        │  Auth · User · Product · Category · Cart ·        │
        │  Order · Wishlist · Review                        │
        └──────────────────────────┬───────────────────────┘
                                   ▼
        ┌──────────────────────────────────────────────────┐
        │  SERVICE LAYER  (@Service)                        │
        │  Business logic · orchestration · mapping         │
        └──────────────────────────┬───────────────────────┘
                                   ▼
        ┌──────────────────────────────────────────────────┐
        │  REPOSITORY LAYER  (Spring Data JPA)              │
        │  9 repositories extending JpaRepository<T, ID>    │
        └──────────────────────────┬───────────────────────┘
                                   ▼
        ┌──────────────────────────────────────────────────┐
        │  DATABASE  ·  MySQL + Hibernate ORM (HikariCP)    │
        └──────────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
E-commerceApplication/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── ECommerceApplication.java        # Spring Boot entry point
│   │   │   │
│   │   │   ├── controller/                      # REST controllers
│   │   │   │   ├── AuthgenticationController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── ProductController.java
│   │   │   │   ├── CategoryController.java
│   │   │   │   ├── CartController.java
│   │   │   │   ├── OrderController.java
│   │   │   │   ├── WishlistController.java
│   │   │   │   └── ReviewController.java
│   │   │   │
│   │   │   ├── service/                         # Business logic
│   │   │   │   ├── AuthenticationService.java
│   │   │   │   ├── UserService.java   ProductService.java
│   │   │   │   ├── CategoryService.java   CartService.java
│   │   │   │   ├── OrderService.java   WishlistService.java
│   │   │   │   └── ReviewService.java
│   │   │   │
│   │   │   ├── dao/                             # Spring Data JPA repositories
│   │   │   │   ├── UserRepository.java   ProductRepository.java
│   │   │   │   ├── CategoryRepository.java   CartRepository.java
│   │   │   │   ├── CartItemRepository.java   OrderRepository.java
│   │   │   │   ├── OrderItemRepository.java   ReviewRepository.java
│   │   │   │   └── WishlistRepository.java
│   │   │   │
│   │   │   ├── entity/                          # JPA entities
│   │   │   │   ├── BaseEntity.java   User.java   Role.java
│   │   │   │   ├── Product.java   Category.java
│   │   │   │   ├── Cart.java   CartItem.java
│   │   │   │   ├── Order.java   OrderItem.java
│   │   │   │   ├── Review.java   Wishlist.java
│   │   │   │
│   │   │   ├── dto/                             # Request / response DTOs
│   │   │   │   ├── RegisterRequest.java
│   │   │   │   ├── AuthenticationRequest.java
│   │   │   │   └── AuthenticationResponse.java
│   │   │   │
│   │   │   └── security/                        # Spring Security + JWT
│   │   │       ├── SecurityConfiguration.java
│   │   │       ├── ApplicationConfig.java
│   │   │       ├── JwtAuthenticationFilter.java
│   │   │       └── JwtService.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/example/ec/
│       └── ECommerceApplicationTests.java
├── pom.xml
├── mvnw / mvnw.cmd                              # Maven wrapper
└── README.md
```

---

## 🗃 Domain Model

The application maps **10 JPA entities**. `User` is also the Spring Security principal (`implements UserDetails`).

### Relationships

| Owner | Relationship | Target | Mapping |
|---|---|---|---|
| `User` | `@OneToOne` | `Cart` | owning side, `cart_id`, `cascade = ALL` |
| `User` | `@OneToMany` | `Order` | `mappedBy = "user"` |
| `User` | `@OneToMany` | `Review` | `mappedBy = "user"` |
| `User` | `@OneToMany` | `Wishlist` | `mappedBy = "user"` |
| `Cart` | `@OneToMany` | `CartItem` | `mappedBy = "cart"` |
| `CartItem` | `@ManyToOne` | `Product` | `product_id`, `FetchType.LAZY` |
| `Category` | `@OneToMany` | `Product` | `mappedBy = "category"`, `cascade = ALL` |
| `Product` | `@ManyToOne` | `Category` | `category_id` |
| `Order` | `@ManyToOne` | `User` | `user_id` |
| `Order` | `@OneToMany` | `OrderItem` | `mappedBy = "order"` |
| `OrderItem` | `@ManyToOne` | `Product` | `product_id`, `FetchType.LAZY` |
| `Review` | `@ManyToOne` | `User`, `Product` | `user_id`, `product_id` |
| `Wishlist` | `@ManyToOne` | `User`, `Product` | `user_id`, `product_id` |

### Diagram

```
                         ┌────────────┐
                         │  Category  │
                         └─────┬──────┘
                        1 │ @OneToMany (cascade ALL)
                          ▼ *
┌──────────┐  1      1 ┌───────┐        ┌────────────┐
│   User   │──────────►│ Cart  │        │  Product   │◄──── @ManyToOne(LAZY)
│(UserDet- │ @OneToOne └───┬───┘        └─────┬──────┘   from CartItem/OrderItem
│  ails)   │           1 │ @OneToMany         │ 1
└────┬─────┘             ▼ *                  │ @OneToMany (reviews, wishlist,
     │               ┌──────────┐             │             cartItems, orderItems)
     │ @OneToMany    │ CartItem │─────────────┘
     ├──────────────►│  Order   │──@OneToMany──► OrderItem ──@ManyToOne──► Product
     │ @OneToMany    └──────────┘
     ├──────────────► Review    ──@ManyToOne──► Product
     │ @OneToMany
     └──────────────► Wishlist  ──@ManyToOne──► Product
```

> `BaseEntity` (`id`, `createdAt`, `updatedAt`, `active`) exists as an auditing scaffold. See [Engineering Notes](#-roadmap--engineering-notes) for how it could become a `@MappedSuperclass`.

---

## 🔐 Authentication & Security

Authentication is **JWT-based and stateless** (`SessionCreationPolicy.STATELESS`).

**Flow**

1. **Register** — `POST /api/auth/register` creates a user (password hashed with **BCrypt**) and returns a signed JWT.
2. **Authenticate** — `POST /api/auth/authenticate` verifies credentials via `AuthenticationManager` and returns a fresh JWT.
3. **Access** — clients send the token on subsequent requests:
   ```
   Authorization: Bearer <token>
   ```
   `JwtAuthenticationFilter` extracts and validates the token (signature + expiry), loads the `UserDetails`, and populates the `SecurityContext`.

**Details**

- **Algorithm:** HS256, signed with a Base64 secret key (`JwtService`)
- **Password encoding:** `BCryptPasswordEncoder`
- **Principal:** `User implements UserDetails`, single authority `ROLE_USER`

> ⚠️ **Current state:** `SecurityConfiguration` permits `requestMatchers("/api/**").permitAll()`, so the JWT filter is wired in and functional, but the API routes are **not yet locked down**. Tightening this (e.g. `permitAll` only on `/api/auth/**`, `authenticated()` elsewhere) is a one-line change — see the roadmap.

---

## 🌐 API Reference

Base URL: `http://localhost:8080`

### 🔑 Auth — `/api/auth`
| Method | Endpoint | Description | Payload |
|---|---|---|---|
| `POST` | `/api/auth/register` | Register a user, returns JWT | `{ firstname, lastname, email, password }` |
| `POST` | `/api/auth/authenticate` | Log in, returns JWT | `{ email, password }` |

### 👤 Users — `/api/users`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/users` | Create a user (body: `User`) |
| `GET` | `/api/users` | List all users |
| `GET` | `/api/users/{id}` | Get user by id |
| `PUT` | `/api/users/{id}` | Update user |
| `DELETE` | `/api/users/{id}` | Delete user by id |
| `DELETE` | `/api/users/deleteAllUsers` | Delete all users |

### 📦 Products — `/api/products`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/products` | Create a product (body: `Product`) |
| `GET` | `/api/products` | List all products |
| `GET` | `/api/products/{id}` | Get product by id |
| `PUT` | `/api/products/{id}` | Update product |
| `DELETE` | `/api/products/{id}` | Delete product |
| `GET` | `/api/products/search?keyword=` | Search products by name (contains, case-insensitive) |
| `GET` | `/api/products/category/{id}` | List products in a category |

### 🏷 Categories — `/api/categories`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/categories` | Create a category (body: `Category`) |
| `GET` | `/api/categories` | List all categories |
| `GET` | `/api/categories/{id}` | Get category by id |
| `PUT` | `/api/categories/{id}` | Update category |
| `DELETE` | `/api/categories/{id}` | Delete category |
| `DELETE` | `/api/categories/deleteAllCategory` | Delete all categories |

### 🛒 Cart — `/api/cart`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/cart/items?userId=&productId=&quantity=` | Add an item to a user's cart |
| `GET` | `/api/cart?userId=` | Get a user's cart |
| `PUT` | `/api/cart/items/{id}?quantity=` | Update a cart item's quantity |
| `DELETE` | `/api/cart/items/{id}` | Remove a cart item |
| `DELETE` | `/api/cart/clear?userId=` | Clear a user's cart |

### 📋 Orders — `/api/orders`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/orders?userId=` | Create an order for a user |
| `GET` | `/api/orders` | List all orders |
| `GET` | `/api/orders/{id}` | Get order by id |
| `PATCH` | `/api/orders/{id}/cancel` | Cancel an order |

### ❤️ Wishlist — `/api/wishlist`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/wishlist/{productId}?userId=` | Add a product to a wishlist |
| `GET` | `/api/wishlist?userId=` | Get a user's wishlist |
| `DELETE` | `/api/wishlist/{productId}?userId=` | Remove a product from a wishlist |

### ⭐ Reviews — `/api/reviews`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/reviews?userId=&productId=&rating=&comment=` | Create a review |
| `GET` | `/api/reviews/product/{productId}` | List reviews for a product |
| `PUT` | `/api/reviews/{reviewId}?rating=&comment=` | Update a review |
| `DELETE` | `/api/reviews/{reviewId}` | Delete a review |

---

## 🚀 Getting Started

### Prerequisites

- **JDK 21** — [Adoptium Temurin](https://adoptium.net/)
- **MySQL 8+** — [Download](https://dev.mysql.com/downloads/)
- **Maven 3.9+** *(optional — the project ships the `mvnw` wrapper)*
- **Git** and, optionally, **Postman** for testing

### 1. Clone

```bash
git clone https://github.com/sm7602/E-commerceApplication.git
cd E-commerceApplication
```

### 2. Create the database

```sql
CREATE DATABASE ecommerce;
```

Hibernate creates the tables automatically on first run (`ddl-auto=update`).

### 3. Configure credentials

Edit `src/main/resources/application.properties` with your MySQL username/password (see [Configuration](#-configuration)). Prefer environment variables over committing secrets.

### 4. Build & run

```bash
# Using the Maven wrapper (recommended)
./mvnw spring-boot:run          # macOS / Linux
mvnw.cmd spring-boot:run        # Windows

# Or build a jar and run it
./mvnw clean package
java -jar target/E-commerceApplication-0.0.1-SNAPSHOT.jar
```

The API starts on **`http://localhost:8080`**.

### 5. Smoke test

```bash
# Register a user and receive a JWT
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"firstname":"Alice","lastname":"Smith","email":"alice@example.com","password":"secret123"}'

# Response: { "token": "eyJhbGciOiJIUzI1NiJ9..." }

# Use the token on protected calls (once routes are locked down)
curl http://localhost:8080/api/products \
  -H "Authorization: Bearer <token>"
```

---

## ⚙ Configuration

`src/main/resources/application.properties`:

```properties
server.port=8080

# ── Datasource ─────────────────────────────
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password

# ── JPA / Hibernate ────────────────────────
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> 🔒 **Security tip:** Don't commit real credentials. Externalize them, e.g.
> `spring.datasource.password=${DB_PASSWORD}` and export `DB_PASSWORD` in your environment.
> The JWT signing key in `JwtService` should likewise move to a property / env variable.

---

## 🧭 Roadmap & Engineering Notes

Ideas to take this from a solid learning project toward production readiness:

**Security**
- [ ] Lock down routes — `permitAll` only on `/api/auth/**`, `authenticated()` for the rest
- [ ] Externalize the JWT secret and DB password (env vars / Vault)
- [ ] Introduce role-based authorization (the `Role` enum currently has only `USER`); add `ADMIN`/`SELLER` and guard write endpoints
- [ ] Add a refresh-token flow and review token TTL

**API design & robustness**
- [ ] Add a global `@RestControllerAdvice` exception handler with consistent error responses
- [ ] Return DTOs (not entities) from controllers; apply `@Valid` on request bodies
- [ ] Add pagination & sorting to list endpoints (`Pageable`)
- [ ] Replace `System.out.println` calls with SLF4J logging

**Code & schema**
- [ ] Convert `BaseEntity` to a `@MappedSuperclass` (with `@CreatedDate` / `@LastModifiedDate`) so entities inherit auditing instead of it being a standalone table
- [ ] Standardize on constructor injection (some controllers use field `@Autowired`)
- [ ] Rename `AuthgenticationController` → `AuthenticationController` (typo)

**Delivery**
- [ ] JUnit 5 + Mockito unit tests and `@DataJpaTest` slices
- [ ] OpenAPI / Swagger UI (`springdoc-openapi`)
- [ ] Dockerfile + `docker-compose` (app + MySQL)
- [ ] CI pipeline (GitHub Actions: build, test, package)

---

## 👨‍💻 Author

**Souvik Maity** — Java / Spring Boot Backend Developer

[![GitHub](https://img.shields.io/badge/GitHub-sm7602-181717?style=for-the-badge&logo=github)](https://github.com/sm7602)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/souvik-maity-2a6759333)
[![Email](https://img.shields.io/badge/Email-Contact-D14836?style=for-the-badge&logo=gmail)](mailto:sm2496444l@gmail.com)

---

## 📄 License

Released under the **MIT License**. See [`LICENSE`](LICENSE) for details.

---

<div align="center">

**⭐ If this project helped you, consider giving it a star!**

*Built with Spring Boot 3 · Java 21 · Spring Security (JWT) · MySQL*

</div>
