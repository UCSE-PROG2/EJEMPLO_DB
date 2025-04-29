# Sistema de Gestión de Libros

## Configuración de la Base de Datos

### 1. Creación de la Base de Datos

```sql
CREATE DATABASE biblioteca;
USE biblioteca;
```

### 2. Creación de Tablas

```sql
-- Tabla de Géneros
CREATE TABLE generos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    es_activo BOOLEAN DEFAULT TRUE
);

-- Tabla de Libros
CREATE TABLE libros (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    fecha_publicacion DATE,
    genero_id BIGINT,
    es_activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (genero_id) REFERENCES generos(id)
);
```

### 3. Inserción de Datos de Ejemplo

```sql
-- Insertar Géneros
INSERT INTO generos (nombre, descripcion) VALUES
('Ficción', 'Libros de ficción y novelas'),
('Ciencia Ficción', 'Libros de ciencia ficción y fantasía'),
('No Ficción', 'Libros basados en hechos reales');

-- Insertar Libros
INSERT INTO libros (titulo, autor, fecha_publicacion, genero_id) VALUES
('Cien años de soledad', 'Gabriel García Márquez', '1967-05-30', 1),
('1984', 'George Orwell', '1949-06-08', 2),
('El señor de los anillos', 'J.R.R. Tolkien', '1954-07-29', 2),
('El principito', 'Antoine de Saint-Exupéry', '1943-04-06', 1),
('Sapiens: De animales a dioses', 'Yuval Noah Harari', '2011-02-10', 3),
('El código Da Vinci', 'Dan Brown', '2003-03-18', 1),
('Dune', 'Frank Herbert', '1965-08-01', 2),
('El arte de la guerra', 'Sun Tzu', '0000-01-01', 3),
('Harry Potter y la piedra filosofal', 'J.K. Rowling', '1997-06-26', 2),
('El alquimista', 'Paulo Coelho', '1988-01-01', 1);
```

## Configuración del Proyecto

1. Clonar el repositorio
2. Actualizar las credenciales de MySQL en `src/main/resources/hibernate.cfg.xml` si es necesario
3. Ejecutar el proyecto.

## Estructura del Proyecto

- `src/main/java/org/example/models/`: Entidades JPA
- `src/main/java/org/example/dto/`: Objetos de Transferencia de Datos
- `src/main/java/org/example/services/`: Lógica de negocio
- `src/main/resources/`: Configuración de Hibernate

## Características

- CRUD completo de libros
- Soft delete para libros
- Relación muchos a uno entre libros y géneros
- Interfaz de consola para gestión de libros

## Notas

- El proyecto utiliza Hibernate para el mapeo objeto-relacional
- La base de datos se actualiza automáticamente al iniciar la aplicación
- Los libros eliminados se marcan como inactivos (soft delete) 