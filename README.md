# Proyecto ProfesorInterino

Aplicación web desarrollada en Java Spring Boot para gestionar y buscar **centros educativos públicos** cargados desde un fichero CSV. Pensado para que los profesores puedan buscar centros por nombre, localidad o provincia.

## 🌍 Versión desplegada en AWS

Esta aplicación puede ejecutarse en una instancia EC2 de Amazon Web Services, conectada a una base de datos MySQL en Amazon RDS.

- ⚙️ Puerto por defecto: `8080`
- 🌐 Acceso típico (si estás en EC2): `http://<IP_PUBLICA_EC2>:8080`
- 🔐 El endpoint de carga solo se puede llamar mediante POST (`curl` o Postman)

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

| Nombre        | Tipo   | Descripción                                               |
|---------------|--------|-----------------------------------------------------------|
| `nombre`      | String | Parte del nombre del centro (no sensible a mayúsculas)    |
| `provinciaId` | Long   | ID de la provincia                                        |
| `localidadId` | Long   | ID de la localidad                                        |

Ejemplo:
```
GET http://localhost:8080/api/centros/buscar?nombre=IES&provinciaId=28
```

### Cargar CSV manualmente
```
POST /api/carga
```
- 🔒 No se puede invocar desde navegador, solo mediante herramientas como `curl` o Postman.
- 💡 Utiliza este endpoint si quieres recargar los datos manualmente desde el fichero CSV.

Ejemplo con `curl`:
```bash
curl -X POST http://<IP_PUBLICA_EC2>:8080/api/carga
```

## 📁 Carga de datos

El fichero CSV de centros se puede cargar:
- **Automáticamente al iniciar la aplicación**
- **Manualmente mediante POST a `/api/carga`**

Detalles:
- Ruta: `src/main/resources/listado_centros.csv`
- Delimitador: `$`
- Configuración:

```properties
centros.csv.path=src/main/resources/listado_centros.csv
centros.csv.delimiter=$
```

## ⚙️ Configuración para H2 (modo local de prueba)

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

Credenciales por defecto:
- JDBC URL: `jdbc:h2:mem:centrosdb`
- User: `sa`
- Password: *(vacío)*

## ☁️ Configuración para AWS RDS (modo producción)

```properties
spring.datasource.url=jdbc:mysql://<ENDPOINT_RDS>:3306/centros_educativos
spring.datasource.username=admin
spring.datasource.password=******
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

⚠️ Las credenciales deben mantenerse seguras en producción. Puedes pasarlas como argumentos al ejecutar el `.jar`.

## 🛠️ Requisitos para ejecutar el proyecto

- Java 17
- Maven 3.8+
- IDE recomendado: Eclipse, IntelliJ o VS Code

### Para ejecutar en local:

```bash
mvn clean install
mvn spring-boot:run
```

### Para empaquetar el `.jar`:

```bash
mvn clean package
```

Y luego:

```bash
java -jar target/miapp-0.0.1-SNAPSHOT.jar
```

## 📌 Notas adicionales

- 🔓 **Sin seguridad activada**: No hay login ni autenticación por ahora.
- 📦 **DTO**: Se usa para exponer solo los datos necesarios al frontend.
- 🌐 **CORS** habilitado por defecto.
- 🧹 El sistema puede detectar duplicados si se carga el CSV varias veces (el campo `codigo` debería ser único).

## ✅ Objetivo del proyecto

Este proyecto permite:
- Buscar centros educativos de forma eficiente.
- Practicar arquitectura en capas con Spring Boot.
- Cargar datos desde un fichero real y normalizar su estructura.
- Desplegar en AWS usando EC2 y RDS.

Ideal para estudiantes de DAW, DAM o FP que quieran aprender arquitectura web moderna en Java.
