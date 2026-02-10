# Roadmap

We work from the dev branch, creating branch for each feature.

## Models to implement
- [ ] Filmografia
- [ ] Acceso
- [ ] Clasificacion
- [ ] Cuenta
- [ ] Genero
- [ ] Factura
- [ ] Film_Genero
- [ ] Pais
- [ ] Reparto
- [ ] Suscripcion

## Core to implement
- [x] pom.xml
- [x] env/env_template
- [ ] Main
- [ ] DBManager
- [ ] Daos
  - [ ] FilmografiaDao

## Features to implement
- [ ] filmografia table
  - [ ] read all the table
  - [ ] read one db entry with an id
  - [ ] insert a new entry in the table
  - [ ] update an entry
  - [ ] delete an entry

## Structure of files
- Main.java
- [package] daos
  - FilmografiaDao.java
  - ...
- [package] models
  - Filmografia.java
  - ...
- [package] utils
  - DBManager.java
