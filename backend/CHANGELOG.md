# Changelog

## [0.1.0] - 2025-10-28
### ✨ Features
- Implemented **User Registration** and **Login** with JWT authentication (HS256).
- Added BCrypt password encryption.
- Created initial **User** and **Role** entities.
- Added **JWT filter** and stateless Spring Security configuration.
- Configured **H2 Database** for development and seed admin user.
- Added **application.yml** and **application-prod.yml** profiles.

### 🧩 Infrastructure
- Stack: Java 21 + Spring Boot 3 + JPA + Validation.
- Included dependencies: jjwt-api, jjwt-impl, jjwt-jackson.
- Configured basic exception handling and stateless security setup.

---

🔖 **v0.1.0 Summary:**  
> This release delivers the complete **authentication module**, including secure **user registration**, **JWT-based login**, and core backend configuration. It establishes the foundation for future modules such as **Product CRUD** and **Authorization by roles**.
