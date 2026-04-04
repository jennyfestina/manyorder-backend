# ManyOrder Backend

ManyOrder is a multi-tenant ordering system backend built with Java and Spring Boot.  
It allows customers to place orders, merchants to manage orders (including manual orders), and admins to monitor the system.

---

## Tech Stack

- Java 21
- Spring Boot
- PostgreSQL
- JPA / Hibernate
- Docker
- Swagger / OpenAPI

---

## Features

### Order Management
- Customer can create orders
- Merchant can manually create orders (e.g. WhatsApp / walk-in)
- Order status tracking (UNFULFILLED, READY, OUT_FOR_DELIVERY, etc.)

### Multi-Tenant Design
- Customers belong to merchants
- Orders are scoped per merchant

### API Design
- RESTful API structure
- DTO-based request/response (no entity exposure)
- Input validation using Jakarta Validation
- Proper HTTP status handling (400, 404)

---

## API Documentation

Run the application locally and access Swagger UI at:

http://localhost:8080/swagger-ui/index.html

---

## Project Status

Core order module completed.  
Currently expanding features and improving production readiness.