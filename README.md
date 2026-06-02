# USA Presidents Data API

A modern, production-ready REST API built with **Java 21** and **Spring Boot 4**, designed to manage and analyze historical data of US presidents, their administrations, elections, marriages, and hobbies. 

The primary focus of this project is to demonstrate clean code principles, enterprise-grade architecture patterns, dynamic querying, and DevOps readiness.

---

## 📂 Project Structure

The project follows a clean **Feature-by-Package** structure to maximize modularity and maintainability:

```text
com.usapresidents.data
│
├── core                       # Shared infrastructure and central components
│   ├── domain
│   │   ├── models             # JPA Entities (President, Election, etc.)
│   │   └── repositories       # Spring Data JPA Repositories
│   ├── dto                    # Core DTOs (PagedResponseDto, SlicedResponseDto)
│   └── exception              # Global exception handling & custom exceptions
│
└── features                   # Independent vertical slices of business features
    ├── administrations        # Manage president administrations and vice presidents
    ├── analytics              # Complex data aggregation for party-level insights
    ├── birthstate             # Filter and paginate presidents by birth state
    ├── childrenranking        # Performance-optimized slicing for children counts
    ├── deceased               # DB-level sorted top lists for deceased presidents
    ├── elections              # Batch loading of election data
    ├── hobbies                # Case-insensitive hobby lookup by president name
    ├── hobby                  # Custom JPQL join queries to find presidents by hobby
    └── search                 # Dynamic filtering using type-safe JPA Specifications
```

---

## 🏗️ Architecture & Design Patterns

This project intentionally moves away from traditional layered architecture ("package-by-layer") and implements a **Vertical Slice Architecture (Feature-by-Package)**. This ensures high modularity, high cohesion, and low coupling, making the codebase highly maintainable and scalable.

### Key Architectural Highlights:
* **Feature-by-Package Layout:** Each business feature (e.g., `analytics`, `childrenranking`, `search`) lives in its own dedicated package containing its respective Controllers, Services, Mappers, and DTOs.
* **Separation of Concerns:** Database entities are strictly decoupled from the REST API layer. Data transfer is handled via immutable **Java Records (DTOs)**, transformed by stateless `@Component` mappers.
* **Centralized Error Handling:** A global `@RestControllerAdvice` intercepts business exceptions (e.g., `PresidentNotFoundException`) and returns standardized, clean `ApiErrorResponseDto` payloads with appropriate HTTP status codes.
* **Database Optimization:** Efficient pagination is implemented throughout the API. For performance-critical features like rankings, Spring Data's `Slice` is preferred over `Page` to prevent expensive `COUNT(*)` queries on the database.

---

## 🛠️ Tech Stack & Ecosystem

* **Backend Framework:** Spring Boot 4.0.x (Java 21)
* **Data Access:** Spring Data JPA / Hibernate
* **Database:** PostgreSQL 16 (Production) & H2 (Testing)
* **Boilerplate Reduction:** Project Lombok
* **Validation:** Jakarta Bean Validation (`@Validated`, `@NotBlank`, `@Positive`)
* **Containerization & DevOps:** Docker, Docker-Compose (Multi-Container Setup)
* **Build Tool:** Maven

---

## 🌟 Advanced Features Demonstrated

### 1. Dynamic Filtering with JPA Specifications
In the `search` feature, the API handles complex, dynamic search criteria (filtering by party, birth state, and birth year ranges) without relying on messy string concatenation or unsafe SQL practices. It utilizes the **JPA Specification Executor** to build type-safe, dynamic database queries programmatically.

### 2. Analytical Data Aggregation
The `analytics` feature showcases complex relational data aggregation. It fetches data across multiple tables (`President`, `Administration`, `Election`), streams and transforms the datasets efficiently, and bundles them into a nested, comprehensive response DTO.

### 3. Resilience & DevOps Excellence
The infrastructure is completely containerized. The `docker-compose.yaml` sets up both the application and the PostgreSQL database, configuring environments securely via defaults (`${DB_HOST:localhost}`). It features an automated database **healthcheck** using `pg_isready` to ensure the API container only starts once the database is fully operational.

---

## 🐳 DevOps & Local Setup Guide

This project is fully automated using the **Maven Wrapper** and **Docker Compose**, requiring zero manual software installations on your host machine.

### Prerequisites
Make sure you have **Docker** and **Docker Desktop** installed and running.

### 1. Build the Application locally
Generate the executable compiled `.jar` archive using the bundled environment wrapper (no global Maven required):

* **Linux / macOS / Git Bash:**
  ```bash
  ./mvnw clean package -DskipTests
  ```
* **Windows (CMD):**
  ```cmd
  mvnw clean package -DskipTests
  ```

### 2. Boot the Container Environment
Launch the entire system architecture (Spring Boot Web App + PostgreSQL Database Instance) in detached background mode:

```bash
docker compose up -d --build
```

### 3. Verify System Operations
* **Check Status:** `docker compose ps` (Ensure the database shows state `healthy`)
* **Live Server Logs:** `docker compose logs -f api`
* **Access the API:** The application will be live at `http://localhost:8080/api/...`

### 4. Shutdown and Reset
To stop the environment and securely purge the persistent PostgreSQL volume cache for a complete fresh reinstall:
```bash
docker compose down -v
```

---

## 📂 API Endpoint Overview


| Endpoint | Method | Feature-Package | Description | Paging Strategy |
| :--- | :--- | :--- | :--- | :--- |
| `/api/presidents/search` | `GET` | `search` | Dynamic search by party, state, or birth year range using JPA Specifications. | `Slice` (Dynamic Filter) |
| `/api/presidents/{id}/administrations` | `GET` | `administrations` | Fetch all administrations for a specific president ID. | `Page` (Full metadata) |
| `/api/analysis/party?name={party}` | `GET` | `analytics` | Aggregated political analysis of a specific party (total presidents, mandates, election wins). | None (Direct Object) |
| `/api/presidents/stateborn?name={state}` | `GET` | `birthstate` | Fetch all presidents born in a specific state, sorted alphabetically. | `Page` (Standard Paging) |
| `/api/presidents/ranking/children` | `GET` | `childrenranking` | Ranks presidents by total number of children using an optimized query. | `Slice` (Performance) |
| `/api/presidents/top-oldest` | `GET` | `deceased` | Top 10 oldest deceased presidents, filtering out living ones on DB-level. | Top-10 Limited List |
| `/api/elections/all` | `GET` | `elections` | Fetch a chronological list of election years, candidates, and vote counts. | `Slice` (Batch Loading) |
| `/api/presidents/{presName}/hobbies` | `GET` | `hobbies` | Fetch a case-insensitive list of hobbies for a specific president's name. | `Page` (Full metadata) |
| `/api/presidents/search/hobby?hobby={name}`| `GET` | `hobby` | Custom JPQL search to find all presidents associated with a specific hobby. | `Slice` (Optimized Join) |

