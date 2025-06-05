# Proyecto ProfesorInterino

Aplicación web desarrollada en Java Spring Boot para gestionar y buscar **centros educativos públicos** cargados desde un fichero CSV. Pensado para que los profesores puedan buscar centros por nombre, localidad o provincia.

## 🏗️ Estructura del Proyecto

El proyecto está organizado en cuatro capas principales:

```
Controller → Service → Repository → Entity
```

### 📦 1. Entity
Clases del modelo de datos que se mapean a las tablas de la base de datos mediante JPA.

- `CentroEducativo`
- `Localidad`
- `Provincia`
- `ComunidadAutonoma`

### 🗃️ 2. Repository
Interfaces que extienden `JpaRepository` para acceder a la base de datos de forma automática.

Ejemplo:
```java
List<CentroEducativo> buscar(String nombre, Long provinciaId, Long localidadId);
```

### ⚙️ 3. Service
Contiene la lógica de negocio. Llama a los repositorios y devuelve resultados al controlador.

### 🌐 4. Controller
Define los endpoints REST que se exponen al usuario.

## 🚀 Endpoints disponibles

### Buscar centros educativos
```
GET /api/centros/buscar
```

#### Parámetros opcionales:

| Nombre       | Tipo   | Descripción                                      |
|--------------|--------|--------------------------------------------------|
| `nombre`     | String | Parte del nombre del centro (no sensible a mayúsculas) |
| `provinciaId`| Long   | ID de la provincia                               |
| `localidadId`| Long   | ID de la localidad                               |

Ejemplo:
```
GET http://localhost:8080/api/centros/buscar?nombre=IES&provinciaId=28
```

## 🧠 Funcionamiento interno

### Diagrama de flujo de capas

![Arquitectura](A_flowchart_in_the_image_illustrates_the_layered_a.png)

## 📁 Carga de datos

El fichero CSV de centros se carga automáticamente al iniciar la aplicación.

- Ruta del archivo: `src/main/resources/listado_centros.csv`
- Delimitador usado: `$`
- Configurado en `application.properties`:

```properties
centros.csv.path=src/main/resources/listado_centros.csv
centros.csv.delimiter=$
```

## ⚙️ Configuración (application.properties)

```properties
spring.datasource.url=jdbc:h2:mem:centrosdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.jpa.show-sql=true
server.port=8080
```

## 🧪 Acceso a la base de datos H2

Puedes acceder a la consola H2 desde:
```
http://localhost:8080/h2-console
```

Usa estas credenciales:

- JDBC URL: `jdbc:h2:mem:centrosdb`
- User: `sa`
- Password: *(vacío)*

## 🛠️ Requisitos para ejecutar el proyecto

- Java 17
- Maven 3.8+
- IDE recomendado: Eclipse, IntelliJ o VS Code

### Para ejecutar:

```bash
mvn clean install
mvn spring-boot:run
```

## 📌 Notas adicionales

- **Sin seguridad**: No hay login activado para facilitar las pruebas.
- **DTO**: Se usa un DTO para devolver solo los datos necesarios de cada centro.
- **CORS** habilitado para uso desde frontend externo.

## ✅ Objetivo del proyecto

Este proyecto permite:
- Buscar centros educativos de forma eficiente.
- Practicar arquitectura en capas con Spring Boot.
- Cargar datos desde un fichero real y normalizar su estructura.

Ideal para estudiantes de DAW, DAM o FP que quieran aprender arquitectura web moderna en Java.
