# 🍽️ API de Recetas - Spring Boot

API REST desarrollada con Java y Spring Boot para la gestión de recetas de cocina.
Permite administrar recetas, ingredientes y pasos, aplicando buenas prácticas
de arquitectura por capas y uso de DTOs.

## 🚀 Tecnologías
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- Maven
- H2 / MySQL
- Postman

## 📂 Arquitectura
- controller
- service
- service/impl
- repository
- dto
- mapper
- exception

Arquitectura en capas siguiendo principios de separación de responsabilidades.

## 📌 Funcionalidades
- CRUD de Recetas
- CRUD de Ingredientes
- CRUD de Pasos
- Asociación de ingredientes y pasos a una receta
- DTOs específicos (resumen y detalle)
- Manejo global de excepciones
- Validaciones de datos

## 🔁 Endpoints principales

### Recetas
- GET /api/recetas
- GET /api/recetas/{id}
- POST /api/recetas
- PUT /api/recetas/{id}
- DELETE /api/recetas/{id}

### Ingredientes
- GET /api/ingredientes
- POST /api/ingredientes

### Pasos
- GET /api/pasos/receta/{recetaId}
- POST /api/pasos

## 🧪 Pruebas
Los endpoints fueron testeados utilizando Postman.

## 👨‍💻 Autor
Nico Mega
