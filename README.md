

# Ediae Netflix Backend

## Context

This project was developed as part of an application development training at Ediae, during the Java backend course.

## Project

The goal is to manage a database for a video content website (like Netflix): management of movies, series, users, subscriptions, etc.

## Technologies used

- Java
- Maven
- JDBC (database connection)

## Installation

1. Clone the repository.
   
   ```bash
   git clone https://github.com/GuillaumeEven/ediae_netflix.git
   ```

2. Install Java (recommended version: 17+).
3. Install Maven.
4. Create the database: the SQL creation script is included in the project (e.g., `/src/main/resources/create_db.sql`).
   
   Example command:
   ```sql
   mysql -u <user> -p <database_name> < src/main/resources/create_db.sql
   ```
   
5. Configure the database connection in the configuration file (e.g., copy `env/env_template` to `.env`).
6. Build the project:
   ```bash
   mvn clean install
   ```
7. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.ediae.netflix.Main"
   ```

## Project structure

- `Main.java`: application entry point
- `daos/`: data access objects
- `models/`: data models (entities)
- `utils/`: utilities (e.g., database connection management)

---

# Roadmap

We work from the `dev` branch, creating a branch for each feature.

## Core to implement
- [x] pom.xml
- [x] env/env_template
- [ ] Main
- [ ] DBManager
- [ ] Daos
  - [ ] FilmografiaDao

## Models to implement
- [x] Filmografia
- [x] Acceso
- [x] Clasificacion
- [x] Cuenta
- [x] Genero
- [x] Factura
- [x] Film_Genero
- [x] Pais
- [x] Reparto
- [x] Suscripcion

## DAOs to implement
- [x] BaseDao
- [x] FilmografiaDao extends BaseDao
- [ ] AccesoDao extends BaseDao
- [ ] ClasificacionDao extends BaseDao
- [ ] CuentaDao extends BaseDao
- [ ] GeneroDao extends BaseDao
- [ ] FacturaDao extends BaseDao
- [ ] Film_GeneroDao extends BaseDao
- [ ] PaisDao extends BaseDao
- [ ] RepartoDao extends BaseDao
- [ ] SuscripcionDao extends BaseDao

## Features to implement
- [ ] filmografia table
  - [ ] Read all entries
  - [ ] Read one entry by id
  - [ ] Insert a new entry
  - [ ] Update an entry
  - [ ] Delete an entry


