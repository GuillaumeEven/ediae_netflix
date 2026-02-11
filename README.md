# Roadmap

We work from the dev branch, creating branch for each feature.

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

## Daos to implement
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
