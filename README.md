<div align="center">

# 🛒 E-Commerce Backend API

**A RESTful e-commerce backend built with Spring Boot, Spring Security, and JWT authentication — layered MVC architecture with role-based identity, JPA/Hibernate persistence, and full CRUD across 11 domain entities.**

<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-8.x-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Hibernate-JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
<img src="https://img.shields.io/badge/Status-Learning%20Project-orange?style=for-the-badge"/>

</div>

---

## 📌 Table of Contents

- [About the Project](#-about-the-project)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Domain Model](#-domain-model)
- [Authentication Flow](#-authentication-flow)
- [API Endpoints](#-api-endpoints)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [Key Features](#-key-features)
- [Roadmap & Known Limitations](#-roadmap--known-limitations)
- [What I Learned](#-what-i-learned)
- [Author](#-author)

---

## 🧩 About the Project

This is a backend REST API for an online store, built with **Spring Boot 3.5.3** on **Java 21**. It models the core commerce flow end to end:

- User registration and login with **JWT-based stateless authentication**
- Two roles — `ADMIN` and `CUSTOMER` — backed by a single `users` table via a `UserDetails` implementation
- Product catalogue with category management
- Shopping cart, checkout, and order lifecycle (place → cancel → deliver)
- Wishlist management
- Product reviews and ratings

It was built as a portfolio / learning project to practise real-world backend concerns: a clean layered architecture, JPA/Hibernate relationship mapping, DTO-based request/response contracts with Bean Validation, and a from-scratch JWT security filter chain.

> **Why it matters:** Wiring Spring Security together with a custom JWT filter, a `UserDetailsService`, and a `DaoAuthenticationProvider` — while mapping a non-trivial relational domain (one-to-one, one-to-many, and bidirectional associations) — covers the exact ground a Java backend role expects.

---

## 🛠 Tech Stack

| Layer | Technology | Notes |
|---|---|---|
| Language | Java | 21 (LTS) |
| Framework | Spring Boot | 3.5.3 |
| Web | Spring MVC (REST controllers) | — |
| Security | Spring Security + JJWT `0.13.0` | Stateless, `Bearer` token, HS256 |
| Persistence | Spring Data JPA / Hibernate | `ddl-auto=update` |
| Database | MySQL | via `mysql-connector-j` |
| Validation | Jakarta Bean Validation | `@Valid` on DTOs |
| Boilerplate | Lombok | `@Data`, `@Builder`, `@RequiredArgsConstructor` |
| Build | Maven | wrapper included (`mvnw`) |
| Dev | Spring Boot DevTools | hot reload |

---

## 🏗 Architecture

Standard layered (n-tier) architecture with a clear one-way dependency flow:

```
HTTP Request
    │
    ▼
┌─────────────┐   JWT filter validates the token and populates
│ Controller  │◄─ the SecurityContext before the request is handled
└─────────────┘
    │  DTO in / DTO out
    ▼
┌─────────────┐   Business logic, entity ↔ DTO mapping,
│  Service    │   discount / total calculations
└─────────────┘
    │
    ▼
┌─────────────┐   Spring Data JPA interfaces
│ Repository  │
└─────────────┘
    │
    ▼
┌─────────────┐
│   MySQL     │
└─────────────┘
```

**Security path (per request):**

```
Request ──► JwtAuthenticationFilter ──► JwtService.extractUsername()
                                          │
                                          ▼
                              UserDetailsService.loadUserByUsername()
                                          │
                                          ▼
                              JwtService.isTokenValid()  ──► SecurityContext set
                                          │
                                          ▼
                              UsernamePasswordAuthenticationFilter ──► Controller
```

---

## 📂 Project Structure

```
src/main/java/com/example
├── ECommerceApplication.java          # Spring Boot entry point
│
├── controller/                        # REST endpoints (9 controllers)
│   ├── AuthgenticationController.java  # register + login
│   ├── AdminController.java
│   ├── CustomerController.java
│   ├── ProductController.java
│   ├── CategoryController.java
│   ├── CartController.java
│   ├── OrderController.java
│   ├── ReviewController.java
│   └── WishlistController.java
│
├── service/                           # Business logic (9 services)
│
├── dao/                               # Spring Data JPA repositories (11)
│
├── dto/                               # Request/response objects, grouped by domain
│   ├── auth/    admin/    customer/
│   ├── product/ category/ cart/
│   └── order/   review/   wishlist/
│
├── entity/                            # JPA entities (12)
│   ├── User.java   (implements UserDetails)
│   ├── Role.java   (ADMIN | CUSTOMER)
│   ├── Admin.java      Customer.java
│   ├── Product.java    Category.java
│   ├── Cart.java       CartItem.java
│   ├── Order.java      OrderItem.java
│   ├── Review.java     Wishlist.java
│
└── security/                          # JWT + Spring Security config
    ├── SecurityConfiguration.java     # filter chain, stateless session
    ├── ApplicationConfig.java         # UserDetailsService, AuthProvider, BCrypt
    ├── JwtAuthenticationFilter.java   # OncePerRequestFilter
    └── JwtService.java                # token create / parse / validate

src/main/resources/application.properties
src/test/java/com/example/ec/ECommerceApplicationTests.java
```

---

## 🗄 Domain Model

Twelve entities capture the commerce domain. Key relationships:

| Relationship | Type | Description |
|---|---|---|
| `User` ↔ `Customer` | One-to-One | Auth identity ↔ customer profile |
| `User` ↔ `Admin` | One-to-One | Auth identity ↔ admin profile |
| `Customer` ↔ `Cart` | One-to-One | Each customer owns one cart |
| `Cart` ↔ `CartItem` | One-to-Many | Line items in a cart |
| `Category` ↔ `Product` | One-to-Many | Products grouped by category |
| `Customer` ↔ `Order` | One-to-Many | Order history |
| `Order` ↔ `OrderItem` | One-to-Many | Line items in an order |
| `Product` ↔ `Review` | One-to-Many | Ratings & comments |
| `Customer` ↔ `Wishlist` | One-to-Many | Saved products |

`User` is the security principal — it implements `UserDetails`, stores the BCrypt-hashed password, and exposes a single authority (`ROLE_ADMIN` / `ROLE_CUSTOMER`) derived from its `Role` enum.

---

## 🔐 Authentication Flow

1. **Register** — `POST /api/auth/registerCustomer` (or `registerAdmin`) creates a `User` with a BCrypt-hashed password, provisions the matching `Customer`/`Admin` profile (and an empty `Cart` for customers), and returns a signed JWT.
2. **Login** — `POST /api/auth/authenticate` verifies credentials through the `AuthenticationManager` and returns a fresh JWT.
3. **Access** — clients send the token on subsequent requests:
   ```
   Authorization: Bearer <token>
   ```
   `JwtAuthenticationFilter` extracts and validates it, loads the user, and populates the `SecurityContext`.

Tokens are signed with **HS256** and the session policy is **stateless** — no server-side session is stored.

---

## 🌐 API Endpoints

Base URL: `http://localhost:8080`

### Auth — `/api/auth`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/registerCustomer` | Register a customer, returns JWT |
| POST | `/registerAdmin` | Register an admin, returns JWT |
| POST | `/authenticate` | Login, returns JWT |

### Products — `/api/products`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/` | Create a product |
| GET | `/` | List all products |
| GET | `/{id}` | Get product by id |
| PUT | `/{id}` | Update a product |
| DELETE | `/{id}` | Delete a product |
| GET | `/search?keyword=` | Search by name |
| GET | `/category/{id}` | Products in a category |

### Categories — `/api/categories`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/` | Create a category |
| GET | `/` | List all categories |
| GET | `/{id}` | Get category by id |
| PUT | `/{id}` | Update a category |
| DELETE | `/{id}` | Delete a category |
| DELETE | `/deleteAllCategory` | Delete all categories |

### Cart — `/api/cart`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/items` | Add an item to the cart |
| GET | `/?CartId=` | Get a cart |
| PUT | `/items/{id}` | Update a cart item |
| DELETE | `/items/{id}` | Remove a cart item |
| DELETE | `/clear?userId=` | Clear the cart |

### Orders — `/api/orders`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/` | Create an order |
| GET | `/` | List orders |
| GET | `/{id}` | Get order by id |
| PUT | `/{id}` | Update an order |
| PATCH | `/{id}/cancel` | Cancel an order |
| PATCH | `/{id}/deliver` | Mark as delivered |

### Reviews — `/api/reviews`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/` | Create a review |
| GET | `/product/{productId}` | Reviews for a product |
| PUT | `/{reviewId}` | Update a review |
| DELETE | `/{reviewId}` | Delete a review |

### Wishlist — `/api/wishlist`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/{productId}` | Add product to wishlist |
| GET | `/` | Get wishlist |
| DELETE | `/{productId}` | Remove from wishlist |

### Customers — `/api/customer` &nbsp;·&nbsp; Admins — `/api/v1/admins`
Standard CRUD (`POST` / `GET` / `GET /{id}` / `PUT /{id}` / `DELETE /{id}`).

> ℹ️ **Note:** all `/api/**` routes are currently open (see [Known Limitations](#-roadmap--known-limitations)). The JWT infrastructure is fully wired but not yet enforced on business endpoints.

---

## 🚀 Getting Started

### Prerequisites
- **JDK 21+**
- **MySQL 8.x** running locally
- Maven (or use the bundled `./mvnw` wrapper)

### 1. Clone
```bash
git clone https://github.com/shubhankar360/E-commerceApplication-Jwt-Autentication.git
cd E-commerceApplication-Jwt-Autentication
```

### 2. Create the database
```sql
CREATE DATABASE ecommerce;
```

### 3. Configure credentials
Edit `src/main/resources/application.properties` (see [Configuration](#-configuration)) — or better, supply them as environment variables.

### 4. Run
```bash
./mvnw spring-boot:run          # Linux / macOS
mvnw.cmd spring-boot:run        # Windows
```

The API starts on **http://localhost:8080**. Hibernate creates/updates the schema automatically (`ddl-auto=update`).

### 5. Smoke test
```bash
# Register a customer
curl -X POST http://localhost:8080/api/auth/registerCustomer \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane", "lastName": "Doe",
    "email": "jane@example.com", "password": "secret123",
    "phoneNumber": "9876543210", "dateOfBirth": "1995-05-20",
    "gender": "FEMALE", "addressLine1": "12 Main St",
    "city": "Delhi", "state": "Delhi", "country": "India", "pincode": "110001"
  }'

# Login
curl -X POST http://localhost:8080/api/auth/authenticate \
  -H "Content-Type: application/json" \
  -d '{"email": "jane@example.com", "password": "secret123"}'
```

---

## ⚙️ Configuration

`src/main/resources/application.properties`:

```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Then export the values before running:
```bash
export DB_USERNAME=root
export DB_PASSWORD=yourpassword
```

> 🔒 **Do not commit real credentials.** Use environment variables (as above) or a git-ignored `application-local.properties`. The JWT signing key should likewise be externalised, not hard-coded.

---

## ✨ Key Features

- **Stateless JWT auth** — custom `OncePerRequestFilter`, HS256-signed tokens, no server session.
- **Role-based identity** — single `users` table, `Role` enum, `UserDetails`-backed principal.
- **BCrypt password hashing** via `DaoAuthenticationProvider`.
- **Layered architecture** — controllers stay thin; services own the logic and DTO mapping.
- **DTO pattern** — separate request/response objects per domain keep entities off the wire; `@JsonIgnore` breaks bidirectional serialization loops.
- **Bean Validation** — `@Valid` with `@NotBlank`, `@Email`, `@Pattern`, `@Positive`, etc.
- **Business logic** — automatic SKU generation, discount/total calculation, order-number generation, order lifecycle transitions.

---

## 🧭 Roadmap & Known Limitations

This is an actively evolving learning project. Honest current state:

**Security**
- [ ] **Enforce authorization.** `SecurityConfiguration` currently `permitAll()`s `/api/**`, so the JWT is created and parsed but not required on business endpoints. Next step: permit only `/api/auth/**` and authenticate everything else.
- [ ] **Add method-level rules** (`@PreAuthorize` / `hasRole`) so only admins can mutate products/categories and customers can only touch their own cart/orders.
- [ ] **Derive the current user from the token**, not from request params. Cart and order endpoints take `customerId` / `userId` from the client, which lets one user act as another — resolve it from the `SecurityContext` instead.
- [ ] **Externalise the JWT secret** and the DB password (both are currently in source).
- [ ] **Fix token expiry.** `JwtService` sets `1000 * 60 * 24` ms ≈ **24 seconds**; it was likely meant to be minutes/hours.

**Correctness**
- [ ] **Add `@RequestBody` to the register endpoints.** `registerAdmin` / `registerCustomer` are missing it, so JSON bodies won't bind (only `authenticate` has it).
- [ ] **Review `ProductService.updateProduct`** — it sets `active=false` on every update, which hides the product.

**Robustness & DX**
- [ ] Add a `@RestControllerAdvice` global exception handler (currently `RuntimeException`s surface as raw 500s).
- [ ] Replace `System.out.println` tracing with SLF4J logging.
- [ ] Add real tests beyond `contextLoads()`.
- [ ] Add OpenAPI/Swagger docs (`springdoc-openapi`).
- [ ] Add a `.gitignore` for `/target` and local config.

---

## 📚 What I Learned

- Wiring **Spring Security's filter chain** from scratch — `SecurityFilterChain`, `AuthenticationProvider`, `UserDetailsService`, and a custom JWT filter placed before `UsernamePasswordAuthenticationFilter`.
- Creating, signing, and verifying **JWTs** with the JJWT library.
- Modelling a relational domain with **JPA/Hibernate** — one-to-one, one-to-many, and bidirectional mappings, and taming JSON serialization with `@JsonIgnore`.
- Structuring a real service with the **DTO + layered MVC** pattern.
- Applying **Bean Validation** declaratively at the API boundary.

---

## 👨‍💻 Author

**Souvik Maity** — Java / Spring Boot Backend Developer

[![GitHub](https://img.shields.io/badge/GitHub-sm7602-181717?style=for-the-badge&logo=github)](https://github.com/sm7602)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/souvik-maity-2a6759333)
[![Email](https://img.shields.io/badge/Email-Contact-D14836?style=for-the-badge&logo=gmail)](mailto:sm2496444l@gmail.com)

---

<div align="center">

⭐ If this project helped or interested you, consider giving it a star.

</div>
