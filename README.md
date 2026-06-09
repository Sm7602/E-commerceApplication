# E-commerceApplication
[README.md](https://github.com/user-attachments/files/28753002/README.md)

<div align="center">

<img src="https://img.shields.io/badge/Spring%20Boot-4.0.6-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-9.4-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Hibernate-7.2-59666C?style=for-the-badge&logo=hibernate&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
<img src="https://img.shields.io/badge/Status-In%20Development-orange?style=for-the-badge"/>

# 🛒 E-Commerce Backend API

### A production-ready RESTful backend built with Spring Boot 4, Hibernate ORM, and MySQL — following clean MVC architecture with full CRUD operations across 9 domain entities.

</div>

---

## 📌 Table of Contents

- [About the Project](#-about-the-project)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Entity Relationship](#-entity-relationship)
- [API Endpoints](#-api-endpoints)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [Key Features](#-key-features)
- [What I Learned](#-what-i-learned)
- [Author](#-author)

---

## 🧩 About the Project

This is a fully functional **E-Commerce REST API** backend built using **Spring Boot 4.0.6** — the latest Spring framework — designed to power an online shopping platform.

The application handles the complete e-commerce flow:
- User registration and authentication
- Product catalogue with category management
- Shopping cart and order processing
- Wishlist management
- Product reviews and ratings

It was built from scratch as a portfolio project to demonstrate real-world backend development skills including layered architecture, JPA/Hibernate ORM mapping, RESTful API design, and relational database modelling.

> **Why this project matters:** Building an e-commerce backend requires handling complex entity relationships (many-to-many wishlists, one-to-many orders, bidirectional mappings) — the exact skills employers look for in a Java backend developer.

---

## 🛠 Tech Stack

| Layer | Technology | Version |
|---|---|---|
| Language | Java | 21 (LTS) |
| Framework | Spring Boot | 4.0.6 |
| Web Layer | Spring MVC (REST) | 7.0.7 |
| ORM | Hibernate | 7.2.12 Final |
| Data Access | Spring Data JPA | 4.0.6 |
| Database | MySQL | 9.4 |
| Connection Pool | HikariCP | Built-in |
| Build Tool | Apache Maven | 3.9+ |
| Boilerplate Reduction | Lombok | 1.18.42 |
| Dev Tools | Spring Boot DevTools | 4.0.6 |
| Runtime | Apache Tomcat | 11.0.21 (Embedded) |

---

## 🏗 Architecture

This project follows a strict **3-Layer MVC Architecture**:

```
┌─────────────────────────────────────────────────────────┐
│                    CLIENT (Postman / Frontend)            │
│                    HTTP Requests (REST/JSON)              │
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│                  CONTROLLER LAYER                         │
│   @RestController  │  Request mapping  │  Input validation│
│                                                           │
│  CartController    ProductController   OrderController    │
│  UserController    CategoryController  ReviewController   │
│  WishlistController                                       │
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│                   SERVICE LAYER                           │
│   @Service  │  Business Logic  │  Transaction Management  │
│                                                           │
│  CartService       ProductService      OrderService       │
│  UserService       CategoryService     ReviewService      │
│  WishlistService                                          │
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│                  REPOSITORY LAYER                         │
│   @Repository  │  Spring Data JPA  │  JPQL Queries        │
│                                                           │
│  CartRepository      ProductRepository   OrderRepository  │
│  UserRepository      CategoryRepository  ReviewRepository │
│  CartItemRepository  OrderItemRepository WishlistRepository│
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│                   DATABASE LAYER                          │
│              MySQL 9.4  │  Hibernate ORM                  │
│        HikariCP Connection Pool  │  9 Tables              │
└─────────────────────────────────────────────────────────┘
```

**Design Principles Applied:**
- **Separation of Concerns** — each layer has a single responsibility
- **Dependency Injection** — Spring IoC container manages all beans
- **DRY (Don't Repeat Yourself)** — shared logic extracted into services
- **RESTful Design** — proper HTTP methods, status codes, and resource naming

---

## 📁 Project Structure

```
E-commerceApplication/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           │
│   │   │           ├── ECommerceApplication.java       ← Main entry point
│   │   │           │
│   │   │           ├── controller/                     ← REST Controllers
│   │   │           │   ├── CartController.java
│   │   │           │   ├── CategoryController.java
│   │   │           │   ├── OrderController.java
│   │   │           │   ├── ProductController.java
│   │   │           │   ├── ReviewController.java
│   │   │           │   ├── UserController.java
│   │   │           │   └── WishlistController.java
│   │   │           │
│   │   │           ├── dao/                            ← Repository Interfaces
│   │   │           │   ├── CartItemRepository.java
│   │   │           │   ├── CartRepository.java
│   │   │           │   ├── CategoryRepository.java
│   │   │           │   ├── OrderItemRepository.java
│   │   │           │   ├── OrderRepository.java
│   │   │           │   ├── ProductRepository.java
│   │   │           │   ├── ReviewRepository.java
│   │   │           │   ├── UserRepository.java
│   │   │           │   └── WishlistRepository.java
│   │   │           │
│   │   │           ├── entity/                         ← JPA Entities
│   │   │           │   ├── BaseEntity.java
│   │   │           │   ├── Cart.java
│   │   │           │   ├── CartItem.java
│   │   │           │   ├── Category.java
│   │   │           │   ├── Order.java
│   │   │           │   ├── OrderItem.java
│   │   │           │   ├── Product.java
│   │   │           │   ├── Review.java
│   │   │           │   ├── User.java
│   │   │           │   └── Wishlist.java
│   │   │           │
│   │   │           └── service/                        ← Business Logic
│   │   │               ├── CartService.java
│   │   │               ├── CategoryService.java
│   │   │               ├── OrderService.java
│   │   │               ├── ProductService.java
│   │   │               ├── ReviewService.java
│   │   │               ├── UserService.java
│   │   │               └── WishlistService.java
│   │   │
│   │   └── resources/
│   │       └── application.properties                  ← DB + App Config
│   │
│   └── test/
│       └── java/
│           └── com/example/
│               └── ECommerceApplicationTests.java
│
├── pom.xml                                             ← Maven Dependencies
├── mvnw / mvnw.cmd                                     ← Maven Wrapper
└── README.md
```

---

## 🗃 Entity Relationship

The application manages **9 JPA entities** with the following relationships:

```
USER ──────────────────────────────────────────────────────────────┐
  │                                                                 │
  │ @OneToOne                                                       │ @OneToMany
  ▼                                                                 ▼
CART ──── @OneToMany ────► CART_ITEM ──── @ManyToOne ────► PRODUCT │ ORDER
  │                                            │                    │   │
  │                                            │ @ManyToOne         │   │ @OneToMany
  │                               @ManyToOne ◄─┘                   │   ▼
  │                                            │                    │ ORDER_ITEM ──── @ManyToOne ────► PRODUCT
  │                                       CATEGORY                  │
  │                                                                  │
  │                                                                  │ @OneToMany
  │                                                                  ▼
  │                                                               REVIEW
  │
  │ @OneToMany
  ▼
WISHLIST ──── @ManyToMany ────► PRODUCT
```

**Relationship Summary:**

| Entity | Relationship | Target Entity |
|---|---|---|
| `User` | @OneToOne | `Cart` |
| `User` | @OneToMany | `Order` |
| `User` | @OneToMany | `Review` |
| `User` | @OneToMany | `Wishlist` |
| `Cart` | @OneToMany | `CartItem` |
| `CartItem` | @ManyToOne | `Product` |
| `Order` | @OneToMany | `OrderItem` |
| `OrderItem` | @ManyToOne | `Product` |
| `Product` | @ManyToOne | `Category` |
| `Product` | @OneToMany | `Review` |
| `Wishlist` | @ManyToMany | `Product` |

---

## 🌐 API Endpoints

### 👤 User API
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/users/register` | Register new user |
| `POST` | `/api/users/login` | User login |
| `GET` | `/api/users/{id}` | Get user profile |
| `PUT` | `/api/users/{id}` | Update user profile |
| `DELETE` | `/api/users/{id}` | Delete user account |

### 📦 Product API
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/products` | Get all products (paginated) |
| `GET` | `/api/products/{id}` | Get product by ID |
| `GET` | `/api/products/category/{id}` | Get products by category |
| `POST` | `/api/products` | Create new product |
| `PUT` | `/api/products/{id}` | Update product |
| `DELETE` | `/api/products/{id}` | Delete product |

### 🛒 Cart API
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/cart/{userId}` | Get user's cart |
| `POST` | `/api/cart/{userId}/add` | Add item to cart |
| `PUT` | `/api/cart/item/{itemId}` | Update cart item quantity |
| `DELETE` | `/api/cart/item/{itemId}` | Remove item from cart |
| `DELETE` | `/api/cart/{userId}/clear` | Clear entire cart |

### 📋 Order API
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/orders/{userId}` | Place new order |
| `GET` | `/api/orders/{orderId}` | Get order details |
| `GET` | `/api/orders/user/{userId}` | Get user's order history |
| `PUT` | `/api/orders/{orderId}/status` | Update order status |
| `DELETE` | `/api/orders/{orderId}` | Cancel order |

### 🏷 Category API
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/categories` | Get all categories |
| `POST` | `/api/categories` | Create category |
| `PUT` | `/api/categories/{id}` | Update category |
| `DELETE` | `/api/categories/{id}` | Delete category |

### ❤️ Wishlist API
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/wishlist/{userId}` | Get user's wishlist |
| `POST` | `/api/wishlist/{userId}/add/{productId}` | Add product to wishlist |
| `DELETE` | `/api/wishlist/{userId}/remove/{productId}` | Remove from wishlist |

### ⭐ Review API
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/reviews/product/{productId}` | Get product reviews |
| `POST` | `/api/reviews/{userId}/{productId}` | Add review |
| `PUT` | `/api/reviews/{reviewId}` | Update review |
| `DELETE` | `/api/reviews/{reviewId}` | Delete review |

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- **Java 17 or 21** — [Download JDK](https://adoptium.net/)
- **MySQL 8+** — [Download MySQL](https://dev.mysql.com/downloads/)
- **Maven 3.9+** — [Download Maven](https://maven.apache.org/download.cgi)
- **Git** — [Download Git](https://git-scm.com/)
- **Postman** (optional, for API testing) — [Download Postman](https://www.postman.com/)

### Installation

**1. Clone the repository**
```bash
git clone https://github.com/your-username/E-commerceApplication.git
cd E-commerceApplication
```

**2. Create the MySQL database**
```sql
CREATE DATABASE ecommerce;
USE ecommerce;
```

**3. Configure database credentials**

Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

**4. Build the project**
```bash
mvn clean install
```

**5. Run the application**
```bash
mvn spring-boot:run
```

Or run the JAR directly:
```bash
java -jar target/E-commerceApplication-0.0.1-SNAPSHOT.jar
```

**6. Verify it's running**

Open your browser or Postman:
```
http://localhost:8080/api/products
```

You should see an empty JSON array `[]` — the app is running! ✅

---

## ⚙ Configuration

Complete `application.properties` setup:

```properties
# ── Server ──────────────────────────────────────
server.port=8080

# ── Database ─────────────────────────────────────
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ── Hibernate / JPA ───────────────────────────────
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# ── HikariCP Connection Pool ──────────────────────
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000

# ── DevTools ──────────────────────────────────────
spring.devtools.restart.enabled=true
```

> ⚠️ **Note:** Set `spring.jpa.hibernate.ddl-auto=create` on first run to auto-generate all tables, then change to `update` for subsequent runs.

---

## ✨ Key Features

- ✅ **Full CRUD** operations across all 9 domain entities
- ✅ **Spring Data JPA** — zero-boilerplate repository layer with 9 repository interfaces
- ✅ **Hibernate ORM** — automatic table generation and schema management
- ✅ **HikariCP** — high-performance connection pooling (auto-configured)
- ✅ **Lombok** — `@Getter`, `@Setter`, `@Builder`, `@RequiredArgsConstructor` to eliminate boilerplate
- ✅ **Complex JPA Relationships** — `@OneToMany`, `@ManyToOne`, `@ManyToMany`, `@OneToOne`
- ✅ **Embedded Tomcat** — no external server deployment needed
- ✅ **Spring DevTools** — hot reload during development
- ✅ **Maven Wrapper** — no Maven installation required to build
- ✅ **RESTful API design** — proper HTTP verbs and resource naming conventions

---

## 🧠 What I Learned

Building this project gave me hands-on experience with:

**Spring Boot & Spring MVC**
- Setting up a Spring Boot project from scratch with `@SpringBootApplication`
- Building REST controllers with `@RestController`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- Understanding the Spring IoC container and how `@ComponentScan` works
- Dependency Injection using `@Autowired` and constructor injection

**JPA & Hibernate ORM**
- Mapping Java classes to database tables using `@Entity`, `@Table`, `@Column`
- Implementing all four JPA relationship types: `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`
- Understanding `mappedBy` — the owning vs inverse side of a relationship
- Using `@JoinColumn` and `@JoinTable` for foreign key and junction table configuration
- `FetchType.LAZY` vs `FetchType.EAGER` and their performance implications
- Cascade operations with `CascadeType`

**Spring Data JPA**
- Extending `JpaRepository<Entity, ID>` to get free CRUD methods
- Writing custom JPQL queries with `@Query`
- Method name query derivation (e.g. `findByEmail`, `findByProductId`)

**Database Design**
- Normalised relational schema with 9 tables
- Junction tables for many-to-many relationships (`wishlist_products`)
- Foreign key constraints and referential integrity

**Problem Solving**
- Debugged and fixed `BeanCreationException` — wrong package scan configuration
- Fixed Hibernate `MappingException` — missing `@ManyToOne` on entity fields
- Resolved `AnnotationException` — `mappedBy` pointing to non-existent field
- Fixed wrong Maven starter artifact IDs

---

## 📊 Database Schema

```sql
-- Auto-generated by Hibernate (ddl-auto=update)

CREATE TABLE users (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(100) UNIQUE NOT NULL,
    password    VARCHAR(255) NOT NULL,
    phone       VARCHAR(15),
    role        ENUM('CUSTOMER','SELLER','ADMIN') DEFAULT 'CUSTOMER',
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE products (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(200) NOT NULL,
    description     TEXT,
    price           DECIMAL(10,2) NOT NULL,
    stock_quantity  INT DEFAULT 0,
    category_id     BIGINT,
    seller_id       BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (seller_id) REFERENCES users(id)
);

CREATE TABLE carts (
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_items (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    cart_id     BIGINT,
    product_id  BIGINT,
    quantity    INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_number    VARCHAR(50) UNIQUE,
    user_id         BIGINT,
    total_amount    DECIMAL(10,2),
    status          ENUM('PENDING','CONFIRMED','SHIPPED','DELIVERED','CANCELLED'),
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id    BIGINT,
    product_id  BIGINT,
    quantity    INT NOT NULL,
    unit_price  DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE wishlists (
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE wishlist_products (
    wishlist_id BIGINT,
    product_id  BIGINT,
    PRIMARY KEY (wishlist_id, product_id),
    FOREIGN KEY (wishlist_id) REFERENCES wishlists(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE reviews (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id     BIGINT,
    product_id  BIGINT,
    rating      INT CHECK (rating BETWEEN 1 AND 5),
    comment     TEXT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

---

## 🔮 Future Enhancements

- [ ] JWT Authentication & Authorization with Spring Security
- [ ] Razorpay / Stripe payment gateway integration
- [ ] Redis caching for product listings
- [ ] Elasticsearch for full-text product search
- [ ] Email notifications using JavaMailSender
- [ ] File upload for product images (AWS S3 / Cloudinary)
- [ ] Swagger / OpenAPI documentation
- [ ] Docker containerisation + Docker Compose
- [ ] Unit & integration tests with JUnit 5 + Mockito

---

## 👨‍💻 Author

**Souvik Maity**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/souvik-maity-2a6759333 )
[![GitHub](https://img.shields.io/badge/GitHub-Follow-181717?style=for-the-badge&logo=github)](https://github.com/sm7602)
[![Email](https://img.shields.io/badge/Email-Contact-D14836?style=for-the-badge&logo=gmail)](mailto:your-sm2496444l@gmail.com)

**BCA Graduate 2026 · Java Full Stack Developer**
Trained at DUCAT IT School · Specialising in Spring Boot Backend Development

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<div align="center">

**⭐ If this project helped you, please give it a star on GitHub!**

*Built with ❤️ using Spring Boot 4 + Java 21 + MySQL*

</div>
